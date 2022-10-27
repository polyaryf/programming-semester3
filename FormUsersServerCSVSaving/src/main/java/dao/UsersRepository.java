package dao;

import model.User;

import java.util.List;

public interface UsersRepository {
    void save(User user);
    List<User> getAll();
    User getById(int id);
    void delete(int id);
}
