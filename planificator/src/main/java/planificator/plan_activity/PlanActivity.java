package planificator.plan_activity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import planificator.plan.Plan;
import planificator.reminder.Reminder;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "plan-activities")
public class PlanActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date startTime;

    private Date endTime;

    private String name;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Plan plan;

    @OneToOne
    private Reminder reminder;
}
