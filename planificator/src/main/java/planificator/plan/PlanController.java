package planificator.plan;

import jakarta.validation.Valid;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import planificator.exceptions.ErrorMessage;
import planificator.exceptions.NoUserException;
import planificator.exceptions.UnauthorizedUserException;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/plans")
public class PlanController {
    @Autowired
    private PlanService planService;

    @PostMapping()
    public Long save(
            @Valid @RequestBody PlanDTO planDTO) {
        return planService.save(planDTO);
    }

    @GetMapping("/by-user-id/{user_id}")
    public PlanDTO getByUserId(@PathVariable("user_id") Long userId) {
        if (userId == null) { // aici probabil (sigur) nu intra
            return null;
        }

        return planService.getByUserId(userId);
    }

    @GetMapping()
    public List<PlanDTO> getAll() {
        return planService.getAll();
    }

    @DeleteMapping("/by-user-id/{user_id}")
    public void deleteByUserId(@PathVariable("user_id") Long userId) {
        planService.deleteByUserId(userId);
    }

    @PutMapping("/by-user-id/{user_id}")
    public PlanDTO updateByUserId(
            @PathVariable("user_id") Long userId,
            @Valid @RequestBody PlanDTO planDTO) {
        return planService.updateByUserId(planDTO, userId);
    }

    @ExceptionHandler({ ObjectNotFoundException.class})
    public ResponseEntity<ErrorMessage> resourceNotFoundException(ObjectNotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ UnauthorizedUserException.class})
    public ResponseEntity<ErrorMessage> unauthorizedUserException(UnauthorizedUserException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.UNAUTHORIZED.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({ NoUserException.class})
    public ResponseEntity<ErrorMessage> unauthorizedUserException(NoUserException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.UNAUTHORIZED.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.UNAUTHORIZED);
    }
}
