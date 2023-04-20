package planificator.plan;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import planificator.plan_activity.PlanActivity;
import planificator.user.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PlanDTO {
    private List<Long> activityIds = new ArrayList<>();
}
