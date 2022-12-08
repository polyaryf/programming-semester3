package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    Integer id;
    String firstName;
    String lastName;
    String email;
    String password;
    Status status;

    Integer photoId;
    String tgUsername;
}
