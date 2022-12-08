package dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
public class UpdatedTeacher {
    Integer photoId;
    String description;
    Integer workExperience;
    String tgUsername;
}
