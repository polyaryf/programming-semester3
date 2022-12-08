package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavouriteLesson {
    Integer id;
    Integer lessonId;
    Integer studentId;
}
