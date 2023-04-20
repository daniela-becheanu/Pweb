package planificator.reminder;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import planificator.plan_activity.PlanActivity;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "reminders")
public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    private Date appearTime;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private PlanActivity associatedPlanActivity;
}
