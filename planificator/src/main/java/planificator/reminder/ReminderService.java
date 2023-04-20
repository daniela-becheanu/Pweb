package planificator.reminder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import planificator.plan_activity.PlanActivity;
import planificator.plan_activity.PlanActivityRepository;

import java.util.List;

@Service
public class ReminderService {
    @Autowired
    private ReminderRepository reminderRepository;

    @Autowired
    private PlanActivityRepository planActivityRepository;

    @Autowired
    private ReminderMapper reminderMapper;

    public Long save(ReminderDTO reminderDTO) {
        Reminder reminder = reminderMapper.fromDTO(reminderDTO);
        PlanActivity planActivity = planActivityRepository.getReferenceById(reminderDTO.getPlanId());

        planActivity.setReminder(reminder);
        reminder.setAssociatedPlanActivity(planActivity);

        return reminder.getId();
    }

    public ReminderDTO getById(Long id) {
        Reminder reminder = reminderRepository.getReferenceById(id);

        if (reminder == null) {
            return null; // HANDLE ERROR: poate trebuie ceva eroare ca nu l-a gasit (obj not found exc)
        }

        ReminderDTO ReminderDTO = reminderMapper.toDTO(reminder);

        return ReminderDTO; // poate faci direct retrun la ce e mai sus
    }

    public List<ReminderDTO> getAll() {
        return reminderMapper.toDTOs(reminderRepository.findAll());
    }

    public void delete(Long id) {
        reminderRepository.deleteById(id);
    }

    public ReminderDTO update(Long id, ReminderDTO updatedReminderDTO) {
        Reminder reminder = reminderRepository.getReferenceById(id);
        reminderMapper.updateFromDTO(updatedReminderDTO, reminder);

        return reminderMapper.toDTO(reminder);
    }
}
