package planificator.plan_activity;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("plan-activities")
public class PlanActivityController {
    @Autowired
    private PlanActivityService planActivityService;

    @PostMapping
    public Long save(
            @Valid @RequestBody PlanActivityDTO planActivityDTO) {

        return planActivityService.save(planActivityDTO);
    }

    @GetMapping("/{id}")
    public PlanActivityDTO getById(@PathVariable("id") Long id) {
        if (id == null) { // aici probabil (sigur) nu intra
            return null;
        }

        return planActivityService.getById(id);
    }

    @GetMapping()
    public List<PlanActivityDTO> getAll() {
        return planActivityService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        planActivityService.delete(id);
    }

    @PutMapping("/{id}")
    public PlanActivityDTO update(
            @PathVariable("id") Long id,
            @Valid @RequestBody PlanActivityDTO planActivityDTO) {
        return planActivityService.update(id, planActivityDTO);
    }
}
