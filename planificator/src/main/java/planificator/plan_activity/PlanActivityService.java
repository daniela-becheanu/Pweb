package planificator.plan_activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import planificator.plan.Plan;
import planificator.plan.PlanRepository;

import java.util.List;

@Service
public class PlanActivityService {
    @Autowired
    private PlanActivityRepository planActivityRepository;

    @Autowired
    private PlanActivityMapper planActivityMapper;

    @Autowired
    private PlanRepository planRepository;

    public Long save(PlanActivityDTO planActivityDTO) {
        PlanActivity planActivity = planActivityMapper.fromDTO(planActivityDTO);
        Plan plan = planRepository.getReferenceById(planActivityDTO.getPlanId());

        planActivity.setPlan(plan);
        planActivityRepository.save(planActivity);
        plan.addActivity(planActivity);

        return planActivity.getId();
    }

    public PlanActivityDTO getById(Long id) {
        PlanActivity planActivity = planActivityRepository.getReferenceById(id);
        //verifica daca planul asta corespunde planului pe care il are userul curent
        if (planActivity == null) {
            return null; // HANDLE ERROR: poate trebuie ceva eroare ca nu l-a gasit (obj not found exc)
        }

        PlanActivityDTO planActivityDTO = planActivityMapper.toDTO(planActivity);

        return planActivityDTO; // poate faci direct retrun la ce e mai sus
    }

    public List<PlanActivityDTO> getAll() {
        return planActivityMapper.toDTOs(planActivityRepository.findAll());
    }
//
    public void delete(Long id) {
        planActivityRepository.deleteById(id);
    }

    public PlanActivityDTO update(Long id, PlanActivityDTO updatedPlanActivityDTO) {
        PlanActivity planActivity = planActivityRepository.getReferenceById(id);
        planActivityMapper.updateFromDTO(updatedPlanActivityDTO, planActivity);

        return planActivityMapper.toDTO(planActivity);
    }
}
