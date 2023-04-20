package planificator.inserted_activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import planificator.security.services.UserDetailsImpl;
import planificator.exceptions.NoUserException;
import planificator.user.User;
import planificator.user.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InsertedActivityService {
    @Autowired
    private InsertedActivityRepository insertedActivityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InsertedActivityMapper insertedActivityMapper;

    public Long save(InsertedActivityDTO insertedActivityDTO) {
        InsertedActivity insertedActivity = insertedActivityMapper.fromDTO(insertedActivityDTO);

        UserDetailsImpl userDetails =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> currentUser = userRepository.findByUsername(userDetails.getUsername());
        currentUser.ifPresentOrElse(insertedActivity::setUser, () -> new NoUserException("no user logged in"));
        insertedActivityRepository.save(insertedActivity);

        return insertedActivity.getId();
    }

    public InsertedActivityDTO getById(Long id) {
        InsertedActivity insertedActivity = insertedActivityRepository.getReferenceById(id);

        if (insertedActivity == null) {
            return null;
        }

        InsertedActivityDTO insertedActivityDTO = insertedActivityMapper.toDTO(insertedActivity);

        return insertedActivityDTO;
    }

    public List<InsertedActivityDTO> getAll() {
        UserDetailsImpl userDetails =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return insertedActivityMapper.toDTOs(insertedActivityRepository.findByUserId(userDetails.getId()));
    }

    public void delete(Long id) {
        insertedActivityRepository.deleteById(id);
    }

    public InsertedActivityDTO update(InsertedActivityDTO updatedInsertedActivityDTO, Long id) {
        InsertedActivity updatedInsertedActivity = insertedActivityMapper.fromDTO(updatedInsertedActivityDTO);
        updatedInsertedActivity.setId(id);

        InsertedActivity oldInsertedActivity = insertedActivityRepository.getReferenceById(id);
        insertedActivityMapper.updateFromDTO(updatedInsertedActivityDTO, oldInsertedActivity);

        return updatedInsertedActivityDTO;
    }
}
