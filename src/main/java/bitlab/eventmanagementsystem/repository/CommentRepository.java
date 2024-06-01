package bitlab.eventmanagementsystem.repository;

import bitlab.eventmanagementsystem.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c WHERE c.event.id = :eventId")
    List<Comment> findAllByEvent(Long eventId);
}
