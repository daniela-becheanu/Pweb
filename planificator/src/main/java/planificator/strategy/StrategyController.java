package planificator.strategy;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import planificator.strategy.StrategyDTO;

import java.util.List;

@RestController
@RequestMapping("/strategies")
public class StrategyController {
    @Autowired
    private StrategyService strategyService;

    @PostMapping()
    public Long save(@Valid @RequestBody StrategyDTO strategyDTO) {
        return strategyService.save(strategyDTO);
    }

    @GetMapping("/{id}")
    public StrategyDTO getById(@PathVariable("id") Long id) {
        if (id == null) { // aici probabil (sigur) nu intra
            return null;
        }

        return strategyService.getById(id);
    }

    @GetMapping()
    public List<StrategyDTO> getAll() {
        return strategyService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete( @PathVariable("id") Long id) {
        strategyService.delete(id);
    }

    @PutMapping("/{id}")
    public StrategyDTO update(
            @PathVariable("id") Long id,
            @Valid @RequestBody StrategyDTO strategyDTO) {
        return strategyService.update(id, strategyDTO);
    }
}
