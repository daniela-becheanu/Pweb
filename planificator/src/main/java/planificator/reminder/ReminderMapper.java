package planificator.reminder;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper
public interface ReminderMapper {
    Reminder fromDTO(ReminderDTO reminderDTO);

    ReminderDTO toDTO(Reminder reminder);

    List<Reminder> fromDTOs(List<ReminderDTO> reminderDTOs);

    List<ReminderDTO> toDTOs(List<Reminder> reminders);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(ReminderDTO planDTO, @MappingTarget Reminder plan);
}
