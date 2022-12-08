package services.teacher.impl;

import dao.TeacherRepository;
import dto.LoginForm;
import dto.SignUpForm;
import dto.TeacherDto;
import dto.UpdatedTeacher;
import exception.IncorrectPasswordException;
import exception.NotFoundException;
import lombok.AllArgsConstructor;
import model.Teacher;
import services.HashService;
import services.teacher.TeacherMapper;
import services.teacher.TeacherService;

import java.util.Optional;

@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final HashService hashService;

    @Override
    public TeacherDto register(SignUpForm signUpForm) {
        Teacher savedTeacher = teacherMapper.toTeacherFrom(signUpForm);
        teacherRepository.save(savedTeacher);
        return teacherMapper.toTeacherDtoFrom(savedTeacher);
    }

    @Override
    public TeacherDto signIn(LoginForm loginForm) {
        Optional<Teacher> optionalTeacher = teacherRepository.getByEmail(loginForm.getEmail());
        if(!optionalTeacher.isPresent()) {
            throw new NotFoundException("User with name " + loginForm.getEmail() + " not found");
        }
        Teacher teacher = optionalTeacher.get();
        if(!hashService.matches(loginForm.getPassword(), teacher.getPassword())) {
            throw new IncorrectPasswordException("Incorrect password");
        }
        return teacherMapper.toTeacherDtoFrom(teacher);
    }

    @Override
    public TeacherDto saveChangesFor(TeacherDto teacherProfile, UpdatedTeacher updatedTeacherProfile) {

        Optional<Teacher> optionalTeacher = teacherRepository.getByEmail(teacherProfile.getEmail());
        if(optionalTeacher.isEmpty()) {
            throw new NotFoundException("User with name " + teacherProfile.getEmail() + " not found");
        }
        Teacher teacher = optionalTeacher.get();

        Teacher updatedTeacher = teacherMapper.toTeacherFrom(updatedTeacherProfile, teacher);
        teacherRepository.update(updatedTeacher);
        return teacherMapper.toTeacherDtoFrom(updatedTeacher);
    }

    @Override
    public Integer teacherExperience(String experience) {
        if(experience == null || experience.isEmpty()) {
            System.out.println(experience);
            return 0;
        } else {
            System.out.println(Integer.valueOf(experience));
            return Integer.valueOf(experience);
        }
    }
}
