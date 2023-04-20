package planificator.priority;

public enum EPriority {
    HIGH(4),
    LOW(3),
    MEDIUM(2),
    URGENT(1);

    public final Integer label;


    EPriority(Integer label) {
        this.label = label;
    }
}
