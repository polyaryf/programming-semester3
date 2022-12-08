package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teacher {
    Integer id;
    String firstName;
    String lastName;
    String email;
    String password;
    Status status;

    Integer photoId;
    String description;
    Integer workExperience;
    String tgUsername;
}
