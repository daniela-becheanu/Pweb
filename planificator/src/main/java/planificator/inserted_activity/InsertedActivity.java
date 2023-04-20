package planificator.inserted_activity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import planificator.priority.EPriority;
import planificator.user.User;

import java.util.Date;

@Entity
@Table(name = "inserted-activities")
@Getter
@Setter
public class InsertedActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    private Date startTime;

    private Date endTime; // deadline

    private Integer timeNeeded; // measuring unit: hours

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EInsertedActivityType insertedActivityType;

    @NotBlank
    @Size(max = 50)
    private String name;

    @Size(max = 300)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EPriority priority;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InsertedActivity )) return false;
        return id != null && id.equals(((InsertedActivity) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
