package planificator.user;

import jakarta.persistence.*;
import lombok.Getter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Setter;
import planificator.inserted_activity.InsertedActivity;
import planificator.plan.Plan;
import planificator.role.Role;
import planificator.strategy.Strategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotBlank
    @Size(max = 60)
    @Column
    private String email;

    @NotBlank
    @Size(max = 100)
    @Column
    private String password;

    @NotBlank
    @Size(max = 30)
    @Column
    private String username;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToOne
    private Plan plan;

    @ManyToOne(fetch = FetchType.LAZY)
    private Strategy strategy;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<InsertedActivity> insertedActivities = new ArrayList<>();

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(User user) {
        this.setPlan(user.getPlan());
        this.setRoles(user.getRoles());
        this.setEmail(user.getEmail());
        this.setId(user.getId());
        this.setPassword(user.getPassword());
        this.setInsertedActivities(user.getInsertedActivities());
        this.setStrategy(user.getStrategy());
        this.setId(user.getId());
    }

    public void addActivity(InsertedActivity insertedActivity) {
        insertedActivities.add(insertedActivity);
        insertedActivity.setUser(this);
    }

    public void removeActivity(InsertedActivity insertedActivity) {
        insertedActivities.remove(insertedActivity);
        insertedActivity.setUser(null);
    }
}
