package planificator.plan_activity;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper
public interface PlanActivityMapper {
    PlanActivity fromDTO(PlanActivityDTO planActivityDTO);

    PlanActivityDTO toDTO(PlanActivity planActivity);

    List<PlanActivity> fromDTOs(List<PlanActivityDTO> planActivityDTOs);

    List<PlanActivityDTO> toDTOs(List<PlanActivity> planActivities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(PlanActivityDTO planDTO, @MappingTarget PlanActivity plan);
}
