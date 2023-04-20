package planificator.plan_activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import planificator.plan.Plan;

import java.util.List;

@Repository
public interface PlanActivityRepository extends JpaRepository<PlanActivity, Long> {
    List<PlanActivity> findByPlanId(Long planId);

    void deleteAllByPlanId(Long planId);
}
