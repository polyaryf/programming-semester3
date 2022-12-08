package dao.impl;

import dao.UsersRepository;
import model.User;

import java.util.List;
import java.util.Optional;

public class UsersRepositoryDBImpl implements UsersRepository {
    @Override
    public User save(User item) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public Optional<User> getById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void delete(Integer id) {

    }
}
