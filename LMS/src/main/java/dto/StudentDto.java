package dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import model.Status;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class StudentDto extends User{
    Integer photoId;
    String tgUsername;
}
