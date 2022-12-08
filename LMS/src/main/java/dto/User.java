package dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import model.Status;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class User {
    Integer id;
    String firstName;
    String lastName;
    String email;
    Status status;
}
