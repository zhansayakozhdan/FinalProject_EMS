package bitlab.eventmanagementsystem.repository;

import bitlab.eventmanagementsystem.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("SELECT b FROM Booking b WHERE b.user.id = :userId")
    List<Booking> findAllBookingsByUserId(Long userId);

    @Query("SELECT b FROM Booking b WHERE b.event.id = :eventId")
    List<Booking> findAllBookingsByEventId(Long eventId);
}
