package dao;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, K> {
    T save(T item);
    List<T> getAll();
    Optional<T> getById(K id);
    void delete(K id);
    void update(T item);
}
