package bitlab.eventmanagementsystem.dto;

import bitlab.eventmanagementsystem.entity.Event;
import bitlab.eventmanagementsystem.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookingDto {
    private Long id;
    private Integer numberOfReservedSeats;
    private String status;
    private String userEmail;
    private Long eventId;
    private Double totalPrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
