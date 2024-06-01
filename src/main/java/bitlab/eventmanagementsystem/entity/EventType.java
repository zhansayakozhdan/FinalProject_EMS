package bitlab.eventmanagementsystem.entity;

import bitlab.eventmanagementsystem.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "EVENT_TYPES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class EventType extends BaseEntity {
    @Column(name = "NAME", nullable = false)
    private String name;
}
