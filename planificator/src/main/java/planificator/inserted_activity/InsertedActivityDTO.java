package planificator.inserted_activity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import planificator.priority.EPriority;

import java.util.Date;

@Getter
@Setter
public class InsertedActivityDTO {
    private Date startTime;

    private Date endTime; // deadline

    private Integer timeNeeded; // measuring unit: hours

    @Enumerated(EnumType.STRING)
    private EInsertedActivityType EInsertedActivityType;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private EPriority priority;
}
