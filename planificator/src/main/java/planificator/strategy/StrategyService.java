package planificator.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import planificator.plan_activity.PlanActivity;
import planificator.plan_activity.PlanActivityRepository;
import planificator.strategy.Strategy;
import planificator.strategy.StrategyDTO;
import planificator.strategy.StrategyMapper;
import planificator.strategy.StrategyRepository;

import java.util.List;

@Service
public class StrategyService {
    @Autowired
    private StrategyRepository strategyRepository;

    @Autowired
    private PlanActivityRepository planActivityRepository;

    @Autowired
    private StrategyMapper strategyMapper;

    public Long save(StrategyDTO strategyDTO) {
        Strategy strategy = strategyMapper.fromDTO(strategyDTO);
        return strategy.getId();
    }

    public StrategyDTO getById(Long id) {
        Strategy strategy = strategyRepository.getReferenceById(id);

        if (strategy == null) {
            return null; // HANDLE ERROR: poate trebuie ceva eroare ca nu l-a gasit (obj not found exc)
        }

        StrategyDTO StrategyDTO = strategyMapper.toDTO(strategy);

        return StrategyDTO; // poate faci direct retrun la ce e mai sus
    }

    public List<StrategyDTO> getAll() {
        return strategyMapper.toDTOs(strategyRepository.findAll());
    }

    public void delete(Long id) {
        strategyRepository.deleteById(id);

        // HANDLE ERROR: poate arunci vreo eroare daca nu gaseste id-ul
    }

    public StrategyDTO update(Long id, StrategyDTO updatedStrategyDTO) {
        Strategy strategy = strategyRepository.getReferenceById(id);
        strategyMapper.updateFromDTO(updatedStrategyDTO, strategy);

        return strategyMapper.toDTO(strategy);
    }
}
