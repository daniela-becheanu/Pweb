package planificator.reminder;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReminderDTO {
    private Date appearTime;

    @NotBlank
    private Long planId;
}
