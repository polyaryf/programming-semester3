package services.lesson;

import dto.CreatedLesson;
import dto.FavouriteLesson;
import dto.LessonDto;
import dto.TeacherDto;
import model.FileInfo;
import model.Lesson;

public interface LessonMapper {
    Lesson toLessonFrom(CreatedLesson createdLesson, FileInfo fileInfo, TeacherDto teacherDto);
    LessonDto toLessonDtoFrom(Lesson lesson);
    FavouriteLesson toFavouriteLessonFrom(Integer lessonId, Integer studentId);
}
