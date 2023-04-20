package planificator.inserted_activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsertedActivityRepository extends JpaRepository<InsertedActivity, Long> {
    List<InsertedActivity> findByUserId(Long userId);
}
