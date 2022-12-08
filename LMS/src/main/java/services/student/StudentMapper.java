package services.student;

import dto.SignUpForm;
import dto.StudentDto;
import dto.UpdatedStudent;
import model.Student;

public interface StudentMapper {
    Student toStudentFrom(SignUpForm signUpForm);
    Student toStudentFrom(UpdatedStudent updatedStudent, Student student);
    StudentDto toStudentDtoFrom(Student student);
}
