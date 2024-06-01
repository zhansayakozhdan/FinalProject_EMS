package bitlab.eventmanagementsystem.dto;

import bitlab.eventmanagementsystem.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String fullName;
    private String phone;
    private List<Role> roles;
}
