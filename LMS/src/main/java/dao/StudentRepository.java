package dao;

import dto.Subscriber;
import model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Integer> {
    Optional<Student> getByEmail(String email);
    List<Subscriber> getSubscribersByTeacherId(Integer id);
}
