package dao.impl;

import dao.UsersRepository;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryTemplImpl implements UsersRepository  {
    private final List<User> data = new ArrayList<>();
    private int incrementValue = 0;

    @Override
    public User save(User item) {
        if (item.getId() != null) {
            data.removeIf(user -> item.getId().equals(item.getId()));
        } else {
            item.setId(incrementValue++);
        }
        data.add(item);
        return item;
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(data);
    }

    @Override
    public Optional<User> getById(Integer id) {
        return data.stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    @Override
    public void delete(Integer id) {
        data.removeIf(item -> item.getId().equals(id));
    }
}
