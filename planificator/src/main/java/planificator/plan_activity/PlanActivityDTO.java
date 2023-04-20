package planificator.plan_activity;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import planificator.plan.Plan;

import java.util.Date;

@Getter
@Setter
public class PlanActivityDTO {
    private Date startTime;

    private Date endTime;

    private String name;

    private String description;

    private Long planId;
}
