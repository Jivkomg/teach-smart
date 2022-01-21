package bg.sofia.uni.fmi.piss.project.wevip.repository;

import bg.sofia.uni.fmi.piss.project.wevip.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, String> {

    @Query(value = "SELECT * FROM events ORDER BY tickets_sold DESC LIMIT 30", nativeQuery = true)
    List<Event> findTop30SoldOut();
}
