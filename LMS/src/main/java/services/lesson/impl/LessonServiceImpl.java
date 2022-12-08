package services.lesson.impl;

import dao.LessonRepository;
import dto.*;
import exception.NotFoundException;
import lombok.AllArgsConstructor;
import model.FileInfo;
import model.Lesson;
import services.lesson.LessonMapper;
import services.lesson.LessonService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class LessonServiceImpl implements LessonService {
    private LessonRepository lessonRepository;
    private LessonMapper lessonMapper;

    @Override
    public LessonDto createLesson(CreatedLesson createdLesson, FileInfo fileInfo, TeacherDto teacherDto) {
        Lesson lesson = lessonMapper.toLessonFrom(createdLesson, fileInfo, teacherDto);
        Lesson savedLesson = lessonRepository.save(lesson);

        return lessonMapper.toLessonDtoFrom(savedLesson);
    }

    @Override
    public List<LessonDto> getLessonsOf(TeacherDto teacherDto) {
        List<Lesson> lessonsFromDB = lessonRepository.getLessonsByTeacherId(teacherDto.getId());
        List<LessonDto> result = new ArrayList<>();
        for (Lesson lesson : lessonsFromDB) {
            result.add(lessonMapper.toLessonDtoFrom(lesson));
        }
        return result;
    }

    @Override
    public List<LessonDto> getAllLessons() {
        List<Lesson> lessonsFromDB = lessonRepository.getAll();
        List<LessonDto> result = new ArrayList<>();
        for (Lesson lesson : lessonsFromDB) {
            result.add(lessonMapper.toLessonDtoFrom(lesson));
        }
        return result;
    }

    @Override
    public void addLikedLesson(FavouriteLesson favouriteLesson) {
        lessonRepository.saveFavouriteLesson(favouriteLesson);
    }

    @Override
    public List<LessonDto> getFavouriteLessonsOf(StudentDto studentDto) {
        List<Lesson> lessonList = lessonRepository.getFavouritesLessonsByStudentId(studentDto.getId());
        System.out.println(lessonList);
        System.out.println("hello");
        List<LessonDto> result = new ArrayList<>();
        for (Lesson lesson : lessonList) {
            result.add(lessonMapper.toLessonDtoFrom(lesson));
        }
        System.out.println(result);
        return result;
    }

    @Override
    public Boolean isFavouriteLessonAdded(Integer lessonId, Integer studentId) {
        return lessonRepository.isFavourite(lessonId, studentId);
    }

    @Override
    public void deleteFavouriteLesson(FavouriteLesson favouriteLesson) {
        lessonRepository.deleteFavouriteLesson(favouriteLesson.getId());
    }

    @Override
    public FavouriteLesson getFavouriteLesson(Integer lessonId, Integer studentId) {
        Optional<FavouriteLesson> optionalFavouriteLesson = lessonRepository.getFavouriteLesson(lessonId, studentId);
        if (!optionalFavouriteLesson.isPresent()) {
            throw new NotFoundException("FavLesson with lesson id: " + lessonId + " and student id:" + studentId + "not found");
        }
        FavouriteLesson favouriteLesson = optionalFavouriteLesson.get();
        return favouriteLesson;
    }
}
