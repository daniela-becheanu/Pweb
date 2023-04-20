package planificator.strategy;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class StrategyDTO {
    @NonNull
    @NotBlank
    private Integer optimalTimeToLearn; // measuring unit: minutes

    private List<Long> userIds = new ArrayList<>();
}
