package planificator.reminder;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import planificator.reminder.ReminderService;

import java.util.List;

@RestController
@RequestMapping("/reminders")
public class ReminderController {
    @Autowired
    private ReminderService reminderService;

    @PostMapping()
    public Long save(@Valid @RequestBody ReminderDTO reminderDTO) {
        return reminderService.save(reminderDTO);
    }

    @GetMapping("/{id}")
    public ReminderDTO getById(@PathVariable("id") Long id) {
        if (id == null) { // aici probabil (sigur) nu intra
            return null;
        }

        return reminderService.getById(id);
    }

    @GetMapping()
    public List<ReminderDTO> getAll() {
        return reminderService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete( @PathVariable("id") Long id) {
        reminderService.delete(id);
    }

    @PutMapping("/{id}")
    public ReminderDTO update(
            @PathVariable("id") Long id,
            @Valid @RequestBody ReminderDTO reminderDTO) {
        return reminderService.update(id, reminderDTO);
    }
}
