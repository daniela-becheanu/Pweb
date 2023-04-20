package planificator.plan;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import planificator.exceptions.NoUserException;
import planificator.exceptions.UnauthorizedUserException;
import planificator.plan_activity.PlanActivity;
import planificator.plan_activity.PlanActivityRepository;
import planificator.role.ERole;
import planificator.security.services.UserDetailsImpl;
import planificator.user.User;
import planificator.user.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PlanService {
    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private PlanMapper planMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlanActivityRepository planActivityRepository;

    public Long save(PlanDTO planDTO) {
        Plan plan = planMapper.fromDTO(planDTO);

        UserDetailsImpl userDetails =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> currentUser = userRepository.findByUsername(userDetails.getUsername());

        if (currentUser.isPresent()) {
            plan.setUser(currentUser.get());
        } else {
            throw new NoUserException("no user signed in");
        }

        currentUser.get().setPlan(plan);

        for (Long planActivityId : planDTO.getActivityIds()) {
            plan.addActivity(planActivityRepository.findById(planActivityId).get());
        }

        planRepository.save(plan); // vezi exceptie daca creeaza un user un alt plan cand deja are unul
        userRepository.save(currentUser.get());

        return plan.getUser().getId();
    }

    public PlanDTO getByUserId(Long userId) throws ObjectNotFoundException {
        User user = userRepository.getReferenceById(userId);
        Plan plan = user.getPlan();

        UserDetailsImpl userDetails =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> currentUser = userRepository.findByUsername(userDetails.getUsername());

        if (currentUser.isPresent()) {
            if (!currentUser.get().getRoles().contains(ERole.ROLE_ADMIN)) {
                if (plan.getUser() == null || !user.getUsername().equals(userDetails.getUsername())) {
                    throw new ObjectNotFoundException(1, "the plan is not yours");
                }
            }
        } else {
            throw new NoUserException("no user signed in");
        }

        return planMapper.toDTO(plan);
    }

    public List<PlanDTO> getAll() {
        UserDetailsImpl userDetails =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> currentUser = userRepository.findByUsername(userDetails.getUsername());

        if (!currentUser.isPresent()) {
            throw new NoUserException("no user signed in");
        }

        if (currentUser.get().getRoles().contains(ERole.ROLE_ADMIN)) {
            return planMapper.toDTOs(planRepository.findAll());
        }

        throw new UnauthorizedUserException("you must be an admin");
    }

    public void deleteByUserId(Long userId) {
        User user = userRepository.getReferenceById(userId);
        Plan plan = user.getPlan();
        
        UserDetailsImpl userDetails =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> currentUser = userRepository.findByUsername(userDetails.getUsername());

        if (!currentUser.get().getRoles().contains(ERole.ROLE_ADMIN)) {
            if (plan.getUser() == null || !plan.getUser().getUsername().equals(userDetails.getUsername())) {
                throw new ObjectNotFoundException(1, "the plan is not yours");
            }
        }

        planRepository.deleteById(userId);
    }

    public PlanDTO updateByUserId(PlanDTO updatedPlanDTO, Long userId) {
        User user = userRepository.getReferenceById(userId);
        Plan plan = user.getPlan();

        UserDetailsImpl userDetails =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> currentUser = userRepository.findByUsername(userDetails.getUsername());

        if (!currentUser.get().getRoles().contains(ERole.ROLE_ADMIN)) {
            if (plan.getUser() == null || !user.getUsername().equals(userDetails.getUsername())) {
                throw new ObjectNotFoundException(1, "the plan is not yours");
            }
        }

        planMapper.updateFromDTO(updatedPlanDTO, plan);
        planActivityRepository.deleteAllByPlanId(plan.getId());

        for (Long planActivityId : updatedPlanDTO.getActivityIds()) {
            Optional<PlanActivity> planActivityToAdd = planActivityRepository.findById(planActivityId);
            if (planActivityToAdd.isPresent()) {
                plan.addActivity(planActivityToAdd.get());
                planActivityToAdd.get().setPlan(plan);
            }
        }

        PlanDTO returnedPlanDTO = planMapper.toDTO(plan);
        returnedPlanDTO.setActivityIds(updatedPlanDTO.getActivityIds());

        return returnedPlanDTO;
    }

}
