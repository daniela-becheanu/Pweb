package planificator.strategy;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper
public interface StrategyMapper {
    Strategy fromDTO(StrategyDTO strategyDTO);

    StrategyDTO toDTO(Strategy strategy);

    List<Strategy> fromDTOs(List<StrategyDTO> strategyDTOs);

    List<StrategyDTO> toDTOs(List<Strategy> strategies);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(StrategyDTO planDTO, @MappingTarget Strategy plan);
}
