package services.lesson;

import dto.*;
import model.FileInfo;

import java.util.List;

public interface LessonService {
    LessonDto createLesson(CreatedLesson createdLesson, FileInfo fileInfo, TeacherDto teacherDto);
    List<LessonDto> getLessonsOf(TeacherDto teacherDto);
    List<LessonDto> getAllLessons();
    void addLikedLesson(FavouriteLesson favouriteLesson);
    List<LessonDto> getFavouriteLessonsOf(StudentDto studentDto);
    Boolean isFavouriteLessonAdded(Integer lessonId, Integer studentId);
    void deleteFavouriteLesson(FavouriteLesson favouriteLesson);
    FavouriteLesson getFavouriteLesson(Integer lessonId, Integer studentId);
}
