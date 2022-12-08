package services.impl;

import dao.UsersRepository;
import dto.RegistrationForm;
import dto.UserDto;
import exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import model.User;
import services.HashService;
import services.UsersService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final HashService hashService;

    @Override
    public UserDto register(RegistrationForm registrationForm) {
        User user = User.builder()
                .firstName(registrationForm.getFirstName())
                .lastName(registrationForm.getLastName())
                .courseName(registrationForm.getCourseName())
                .age(registrationForm.getAge())
                .passwordHash(hashService.hash(registrationForm.getPassword()))
                .build();

        User savedUser = usersRepository.save(user);
        return UserDto.builder()
                .id(savedUser.getId())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .courseName(savedUser.getCourseName())
                .age(savedUser.getAge())
                .build();
    }

    @Override
    public UserDto getProfile(Integer id) {
        Optional<User> optionalUser = usersRepository.getById(id);
        if(!optionalUser.isPresent()) {
            throw new NotFoundException("User with id " + id + " not found");
        }
        User user = optionalUser.get();
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .courseName(user.getCourseName())
                .age(user.getAge())
                .build();
    }

    @Override
    public List<UserDto> getAll() {
        return usersRepository.getAll()
                .stream()
                .map((user -> UserDto.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .courseName(user.getCourseName())
                        .age(user.getAge())
                        .build()))
                .collect(Collectors.toList());
    }
}
