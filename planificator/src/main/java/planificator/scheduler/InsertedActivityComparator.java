package planificator.scheduler;

import planificator.inserted_activity.InsertedActivity;

import java.util.Comparator;

public class InsertedActivityComparator implements Comparator<InsertedActivity> {
    @Override
    public int compare(InsertedActivity ia1, InsertedActivity ia2) {
        if (ia1.getEndTime() == ia2.getEndTime()) {
            if (ia1.getPriority().label > ia2.getPriority().label) {
                return 1;
            }
            if (ia1.getPriority().label < ia2.getPriority().label) {
                return -1;
            }
        }

        if (ia1.getEndTime().compareTo(ia2.getEndTime()) < 0) {
            return 1;
        }

        if (ia1.getEndTime().compareTo(ia2.getEndTime()) > 0) {
            return -1;
        }

        return 0;
    }
}
