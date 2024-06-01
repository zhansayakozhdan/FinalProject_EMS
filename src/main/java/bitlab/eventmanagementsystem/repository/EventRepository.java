package bitlab.eventmanagementsystem.repository;

import bitlab.eventmanagementsystem.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT e FROM Event e "
            + "WHERE e.name ilike concat('%', :search, '%') "
            + "OR e.eventType.name ilike concat('%', :search, '%') ")
    List<Event> search(String search);
}
