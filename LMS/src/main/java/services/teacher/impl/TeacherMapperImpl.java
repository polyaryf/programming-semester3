package services.teacher.impl;

import dao.TeacherRepository;
import dto.SignUpForm;
import dto.TeacherDto;
import dto.UpdatedTeacher;
import lombok.AllArgsConstructor;
import model.Teacher;
import services.HashService;
import services.teacher.TeacherMapper;

@AllArgsConstructor
public class TeacherMapperImpl implements TeacherMapper {
    private final HashService hashService;
    private final TeacherRepository teacherRepository;

    @Override
    public Teacher toTeacherFrom(SignUpForm signUpForm) {
        Teacher teacher = Teacher.builder()
            .firstName(signUpForm.getFirstName())
            .lastName(signUpForm.getLastName())
            .email(signUpForm.getEmail())
            .password(hashService.hash(signUpForm.getPassword()))
            .status(signUpForm.getStatus())
            .build();
        return teacher;
    }

    @Override
    public TeacherDto toTeacherDtoFrom(Teacher teacher) {
        TeacherDto teacherDto = TeacherDto.builder()
            .id(teacher.getId())
            .firstName(teacher.getFirstName())
            .lastName(teacher.getLastName())
            .email(teacher.getEmail())
            .status(teacher.getStatus())
            .photoId(teacher.getPhotoId())
            .description(teacher.getDescription())
            .workExperience(teacher.getWorkExperience())
            .tgUsername(teacher.getTgUsername())
            .build();
        return teacherDto;
    }

    @Override
    public Teacher toTeacherFrom(UpdatedTeacher updatedTeacher, Teacher teacher) {
        return Teacher.builder()
            .id(teacher.getId())
            .firstName(teacher.getFirstName())
            .lastName(teacher.getLastName())
            .email(teacher.getEmail())
            .password(teacher.getPassword())
            .status(teacher.getStatus())
            .photoId(updatedTeacher.getPhotoId())
            .description(updatedTeacher.getDescription())
            .workExperience(updatedTeacher.getWorkExperience())
            .tgUsername(updatedTeacher.getTgUsername())
            .build();
    }
}


