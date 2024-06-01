package bitlab.eventmanagementsystem.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserViewDto {
    private Long id;
    private String email;
    private String fullName;
    private String phone;
    private List<RoleDto> roles;
}
//для отображения пользователей без пароля -> Админу
