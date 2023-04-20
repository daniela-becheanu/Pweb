package planificator.inserted_activity;

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
@RequestMapping("/inserted-activities")
public class InsertedActivityController {
    @Autowired
    private InsertedActivityService insertedActivityService;

    @PostMapping
    public Long save(
            @Valid @RequestBody InsertedActivityDTO insertedActivityDTO) {

        return insertedActivityService.save(insertedActivityDTO);
    }

    @GetMapping("/{id}")
    public InsertedActivityDTO getById(@PathVariable("id") Long id) {
        if (id == null) {
            return null;
        }

        return insertedActivityService.getById(id);
    }

    @GetMapping()
    public List<InsertedActivityDTO> getAll() {
        return insertedActivityService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        insertedActivityService.delete(id);
    }

    @PutMapping("/{id}")
    public InsertedActivityDTO update(
            @PathVariable("id") Long id,
            @Valid @RequestBody InsertedActivityDTO insertedActivityDTO) {
        return insertedActivityService.update(insertedActivityDTO, id);
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
