package planificator.inserted_activity;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import planificator.plan.Plan;
import planificator.plan.PlanDTO;

import java.util.List;

import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;

@Mapper(
        componentModel = "spring",
        nullValueMappingStrategy = RETURN_DEFAULT
)
public interface InsertedActivityMapper {
    InsertedActivity fromDTO(InsertedActivityDTO insertedActivityDTO);

    InsertedActivityDTO toDTO(InsertedActivity insertedActivity);

    List<InsertedActivity> fromDTOs(List<InsertedActivityDTO> insertedActivitiesDTOs);

    List<InsertedActivityDTO> toDTOs(List<InsertedActivity> insertedActivities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(InsertedActivityDTO insertedActivityDTO, @MappingTarget InsertedActivity insertedActivity);
}
