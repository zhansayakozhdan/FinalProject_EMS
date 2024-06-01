package bitlab.eventmanagementsystem.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserUpdate {
    private Long id;

    private String currentPassword;
    private String newPassword;
    private String reNewPassword;
}
