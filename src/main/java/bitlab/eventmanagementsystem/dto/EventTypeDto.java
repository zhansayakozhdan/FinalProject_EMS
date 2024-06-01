package bitlab.eventmanagementsystem.dto;

import bitlab.eventmanagementsystem.entity.base.BaseEntity;
import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class EventTypeDto {
    private Long id;
    private String name;
}
