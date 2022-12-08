package services.student;

import dto.*;

import java.util.List;

public interface StudentService {
    StudentDto register(SignUpForm signUpForm);
    StudentDto signIn(LoginForm loginForm);
    StudentDto saveChangesFor(StudentDto studentProfile, UpdatedStudent updatedStudent);
    List<Subscriber> getSubscribers(TeacherDto teacherDto);
}
