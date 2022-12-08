package services.lesson.impl;

import dto.CreatedLesson;
import dto.FavouriteLesson;
import dto.LessonDto;
import dto.TeacherDto;
import model.FileInfo;
import model.Lesson;
import services.lesson.LessonMapper;

public class LessonMapperImpl implements LessonMapper {

    @Override
    public Lesson toLessonFrom(CreatedLesson createdLesson, FileInfo fileInfo, TeacherDto teacherDto) {
        return Lesson.builder()
                .title(createdLesson.getTitle())
                .description(createdLesson.getDescription())
                .fileId(fileInfo.getId())
                .teacherId(teacherDto.getId())
                .videoLink(createdLesson.getVideoLink())
                .build();
    }

    @Override
    public LessonDto toLessonDtoFrom(Lesson lesson) {
        return LessonDto.builder()
                .id(lesson.getId())
                .title(lesson.getTitle())
                .description(lesson.getDescription())
                .fileId(lesson.getFileId())
                .teacherId(lesson.getTeacherId())
                .videoLink(lesson.getVideoLink())
                .build();
    }

    @Override
    public FavouriteLesson toFavouriteLessonFrom(Integer lessonId, Integer studentId) {
        return FavouriteLesson.builder()
                .lessonId(lessonId)
                .studentId(studentId)
                .build();
    }
}
