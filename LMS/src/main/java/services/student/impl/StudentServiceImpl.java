package services.student.impl;

import dao.StudentRepository;
import dto.*;
import exception.IncorrectPasswordException;
import exception.NotFoundException;
import lombok.AllArgsConstructor;
import model.Student;
import services.HashService;
import services.student.StudentMapper;
import services.student.StudentService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final HashService hashService;

    @Override
    public StudentDto register(SignUpForm signUpForm) {
        Student savedStudent = studentMapper.toStudentFrom(signUpForm);
        studentRepository.save(savedStudent);
        return studentMapper.toStudentDtoFrom(savedStudent);
    }

    @Override
    public StudentDto signIn(LoginForm loginForm) {
        Optional<Student> optionalStudent = studentRepository.getByEmail(loginForm.getEmail());
        if(!optionalStudent.isPresent()) {
            throw new NotFoundException("User with name " + loginForm.getEmail() + " not found");
        }
        Student student = optionalStudent.get();
        if(!hashService.matches(loginForm.getPassword(), student.getPassword())) {
            throw new IncorrectPasswordException("Incorrect password");
        }
        return studentMapper.toStudentDtoFrom(student);
    }

    @Override
    public StudentDto saveChangesFor(StudentDto studentProfile, UpdatedStudent updatedStudent) {
        Optional<Student> optionalStudent = studentRepository.getByEmail(studentProfile.getEmail());
        if(!optionalStudent.isPresent()) {
            throw new NotFoundException("User with name " + studentProfile.getEmail() + " not found");
        }
        Student student = optionalStudent.get();

        Student updatedStudentProfile = studentMapper.toStudentFrom(updatedStudent, student);
        studentRepository.update(updatedStudentProfile);

        return studentMapper.toStudentDtoFrom(updatedStudentProfile);
    }

    @Override
    public List<Subscriber> getSubscribers(TeacherDto teacherDto) {
        return studentRepository.getSubscribersByTeacherId(teacherDto.getId());
    }
}
