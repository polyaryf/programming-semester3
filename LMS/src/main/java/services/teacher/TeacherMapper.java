package services.teacher;

import dto.SignUpForm;
import dto.TeacherDto;
import dto.UpdatedTeacher;
import model.Teacher;

public interface TeacherMapper {
    public Teacher toTeacherFrom(SignUpForm signUpForm);
    public TeacherDto toTeacherDtoFrom(Teacher teacher);
    public Teacher toTeacherFrom(UpdatedTeacher updatedTeacher, Teacher teacher);
}
