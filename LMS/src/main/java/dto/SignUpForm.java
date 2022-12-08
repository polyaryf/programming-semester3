package dto;

import model.Status;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpForm {
    String firstName;
    String lastName;
    String email;
    String password;
    Status status;
}
