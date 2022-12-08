package dto;

import lombok.*;
import model.Status;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginForm {
    String email;
    String password;
    Status status;
}
