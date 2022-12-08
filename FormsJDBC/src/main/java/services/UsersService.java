package services;

import dto.RegistrationForm;
import dto.UserDto;

import java.util.List;

public interface UsersService {
    UserDto register(RegistrationForm user);
    UserDto getProfile(Integer id);
    List<UserDto> getAll();
}
