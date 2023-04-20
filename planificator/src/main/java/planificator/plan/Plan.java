package planificator.plan;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import planificator.plan_activity.PlanActivity;
import planificator.user.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "plans")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(
            mappedBy = "plan",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PlanActivity> activities = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    public void addActivity(PlanActivity planActivity) {
        activities.add(planActivity);
        planActivity.setPlan(this);
    }

    public void removeActivity(PlanActivity planActivity) {
        activities.remove(planActivity);
        planActivity.setPlan(null);
    }
}
