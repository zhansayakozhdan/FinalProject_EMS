package bitlab.eventmanagementsystem.entity;

import bitlab.eventmanagementsystem.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "EVENTS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "EVENT_NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "EVENT_DATE")
    private LocalDate eventDate;

    @Column(name = "EVENT_TIME")
    private LocalTime eventTime;

    @Column(name = "VENUE")
    private String venue;  //место события

    @Column(name = "QUANTITY_OF_TICKETS")
    private Integer quantityOfTickets;

    @Column(name = "TICKET_COST")
    private Double ticketCost;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EVENT_TYPE_ID")
    private EventType eventType;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}