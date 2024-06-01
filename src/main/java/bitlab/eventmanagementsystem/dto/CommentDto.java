package bitlab.eventmanagementsystem.dto;

import bitlab.eventmanagementsystem.entity.Event;
import bitlab.eventmanagementsystem.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private Long id;
    private String value;
    private Long eventId;
    private String userEmail;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
