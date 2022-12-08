package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lesson {
    Integer id;
    String title;
    String description;
    Integer fileId;
    String videoLink;
    Integer teacherId;
}
