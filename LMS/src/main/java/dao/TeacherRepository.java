package dao;

import model.Teacher;

import java.util.Optional;

public interface TeacherRepository extends CrudRepository<Teacher, Integer> {
    Optional<Teacher> getByEmail(String email);
}
