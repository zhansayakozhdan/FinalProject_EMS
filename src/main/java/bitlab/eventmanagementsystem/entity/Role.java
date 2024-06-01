package bitlab.eventmanagementsystem.entity;

import bitlab.eventmanagementsystem.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "ROLES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Role extends BaseEntity implements GrantedAuthority {
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
