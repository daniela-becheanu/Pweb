package planificator.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import planificator.inserted_activity.InsertedActivity;
import planificator.plan.Plan;
import planificator.plan.PlanRepository;
import planificator.plan_activity.PlanActivity;
import planificator.plan_activity.PlanActivityRepository;
import planificator.user.User;

import java.util.List;
import java.util.PriorityQueue;

@Component
public class Scheduler {
    @Autowired
    private PlanActivityRepository planActivityRepository;

    @Autowired
    private PlanRepository planRepository;

    public Plan generateSchedule(User user) {
        List<InsertedActivity> insertedActivities = user.getInsertedActivities();
        Integer optimalTimeToLearn = user.getStrategy().getOptimalTimeToLearn();
        Plan plan = planRepository.save(new Plan());
        user.setPlan(plan);

        PriorityQueue<InsertedActivity> pq = new PriorityQueue<>(new InsertedActivityComparator());
        for (InsertedActivity insertedActivity : insertedActivities) {
            pq.add(insertedActivity);
        }

//        for (InsertedActivity insertedActivity : insertedActivities) {
//            int slotsNumber = (int) Math.ceil(((double) insertedActivity.getTimeNeeded()) / ((double) optimalTimeToLearn));
//            for (int i = 0;  i < slotsNumber; ++i) {
//                PlanActivity planActivity = new PlanActivity();
//                planActivity.setPlan(plan);
//                planActivity.setDescription(insertedActivity.getDescription());
//                planActivity.setName(insertedActivity.getName());
//                planActivity.setStartTime(insertedActivity.getStartTime());
//                planActivity.setEndTime(insertedActivity.getEndTime());
////                planActivityRepository.sa
//            }
//        }

        InsertedActivity insertedActivity1 = pq.poll();
        InsertedActivity insertedActivity2 = pq.poll();

        while(!pq.isEmpty()) {
            if (insertedActivity1 == null) {
                insertedActivity1 = pq.poll();
            }

            if (insertedActivity2 == null) {
                insertedActivity2 = pq.poll();
            }

            int time1 = insertedActivity1.getTimeNeeded();
            int time2 = insertedActivity2.getTimeNeeded();

            int slotsNumber1 = (int) Math.ceil(((double) time1) / ((double) optimalTimeToLearn));
            int slotsNumber2 = (int) Math.ceil(((double) time2) / ((double) optimalTimeToLearn));


        }

        return null;
    }
}
