package dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonDto {
    Integer id;
    String title;
    String description;
    Integer fileId;
    String videoLink;
    Integer teacherId;
}
