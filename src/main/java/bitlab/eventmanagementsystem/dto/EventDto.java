package bitlab.eventmanagementsystem.dto;

import bitlab.eventmanagementsystem.entity.EventType;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EventDto {
    private Long id;
    private String name;
    private String description;
    private LocalDate eventDate;
    private LocalTime eventTime;
    private String venue;  //место события
    private Integer quantityOfTickets;
    private Double ticketCost;
    private EventType eventType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
