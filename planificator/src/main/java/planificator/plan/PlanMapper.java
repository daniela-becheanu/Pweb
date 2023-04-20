package planificator.plan;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper
public interface PlanMapper {
    Plan fromDTO(PlanDTO planDTO);

    PlanDTO toDTO(Plan plan);

    List<Plan> fromDTOs(List<PlanDTO> planDTOs);

    List<PlanDTO> toDTOs(List<Plan> plans);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(PlanDTO planDTO, @MappingTarget Plan plan);
}
