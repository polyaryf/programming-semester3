package services.teacher;

import dto.UpdatedTeacher;
import dto.LoginForm;
import dto.SignUpForm;
import dto.TeacherDto;

public interface TeacherService {
    TeacherDto register(SignUpForm signUpForm);
    TeacherDto signIn(LoginForm loginForm);
    TeacherDto saveChangesFor(TeacherDto teacherProfile, UpdatedTeacher updatedTeacher);
    Integer teacherExperience(String experience);
}
