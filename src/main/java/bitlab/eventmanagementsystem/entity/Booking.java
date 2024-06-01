package bitlab.eventmanagementsystem.entity;

import bitlab.eventmanagementsystem.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "BOOKINGS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking extends BaseEntity {
    @Column(name = "NUMBER_OF_RESERVED_SEATS")
    private Integer numberOfReservedSeats;

    @Column(name = "STATUS")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EVENT_ID")
    private Event event;

    @Column(name = "TOTAL_PRICE")
    private Double totalPrice;
}
