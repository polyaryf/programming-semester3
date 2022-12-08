package services.student.impl;

import dto.SignUpForm;
import dto.StudentDto;
import dto.UpdatedStudent;
import lombok.AllArgsConstructor;
import model.Student;
import services.HashService;
import services.student.StudentMapper;

@AllArgsConstructor
public class StudentMapperImpl implements StudentMapper {
    private final HashService hashService;

    @Override
    public Student toStudentFrom(SignUpForm signUpForm) {
        Student student = Student.builder()
            .firstName(signUpForm.getFirstName())
            .lastName(signUpForm.getLastName())
            .email(signUpForm.getEmail())
            .password(hashService.hash(signUpForm.getPassword()))
            .status(signUpForm.getStatus())
            .build();
        return student;
    }

    @Override
    public Student toStudentFrom(UpdatedStudent updatedStudent, Student student) {
        return Student.builder()
            .id(student.getId())
            .firstName(student.getFirstName())
            .lastName(student.getLastName())
            .email(student.getEmail())
            .password(student.getPassword())
            .status(student.getStatus())
            .photoId(updatedStudent.getPhotoId())
            .tgUsername(updatedStudent.getTgUsername())
            .build();
    }

    @Override
    public StudentDto toStudentDtoFrom(Student student) {
        StudentDto studentDto = StudentDto.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .status(student.getStatus())
                .photoId(student.getPhotoId())
                .tgUsername(student.getTgUsername())
                .build();
        return studentDto;
    }
}
