package bitlab.eventmanagementsystem.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserCreate {
    private String fullName;
    private String email;
    private  String password;
    private String rePassword;
}
//для регистрации пользователя
//для создания нового пользователя через админа
