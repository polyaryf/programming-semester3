package dao;

import dto.FavouriteLesson;
import model.Lesson;

import java.util.List;
import java.util.Optional;

public interface LessonRepository extends CrudRepository<Lesson, Integer>{
    List<Lesson> getLessonsByTeacherId(Integer id);
    FavouriteLesson saveFavouriteLesson(FavouriteLesson favouriteLesson);
    void deleteFavouriteLesson(Integer id);
    List<Lesson> getFavouritesLessonsByStudentId(Integer id);
    Boolean isFavourite(Integer lessonId, Integer studentId);
    Optional<FavouriteLesson> getFavouriteLesson(Integer lessonId, Integer studentId);
}
