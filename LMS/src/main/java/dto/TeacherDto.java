package dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class TeacherDto extends User {
    Integer photoId;
    String description;
    Integer workExperience;
    String tgUsername;
}
