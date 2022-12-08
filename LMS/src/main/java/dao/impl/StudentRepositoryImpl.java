package dao.impl;

import dao.StudentRepository;
import dto.Subscriber;
import model.Status;
import model.Student;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class StudentRepositoryImpl implements StudentRepository {
    private final JdbcTemplate jdbcTemplate;

    private final static String SQL_SELECT_ALL = "select * from student;";
    private final static String SQL_INSERT = "insert into student(first_name, last_name, email, password, status) VALUES (?, ?, ?, ?, ?);";
    private final static String SQL_SELECT_BY_ID = "select * from student where id = ?;";
    private final static String SQL_SELECT_BY_EMAIL = "select * from student where email = ?;";
    private final static String SQL_UPDATE_STUDENT = "update student set photo_id = ?, tg_username = ? where id = ?";
    private final static String SQL_SELECT_SUBSCRIBERS_BY_TEACHER_ID = "select distinct student.id as id, first_name, last_name, photo_id, tg_username  from student join favourite_lesson fl on student.id = fl.student_id join lesson l on fl.lesson_id = l.id where teacher_id = ?;";

    private final RowMapper<Student> studentRowMapper = (row, rowNumber) -> Student.builder()
            .id(row.getInt("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .email(row.getString("email"))
            .password(row.getString("password"))
            .status(Status.valueOf(row.getString("status")))
            .photoId(row.getInt("photo_id"))
            .tgUsername(row.getString("tg_username"))
            .build();

   private final RowMapper<Subscriber> subscriberRowMapper = (row, rowNumber) -> Subscriber.builder()
           .id(row.getInt("id"))
           .firstName(row.getString("first_name"))
           .lastName(row.getString("last_name"))
           .photoId(row.getInt("photo_id"))
           .tgUsername(row.getString("tg_username"))
           .build();

    public StudentRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Student save(Student item) {
        if (item.getId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"id"});
                statement.setString(1, item.getFirstName());
                statement.setString(2, item.getLastName());
                statement.setString(3, item.getEmail());
                statement.setString(4, item.getPassword());
                statement.setString(5, String.valueOf(item.getStatus()));
                return statement;
            }, keyHolder);
            item.setId(keyHolder.getKey().intValue());
            return item;
        } else {
            return null;
        }
    }

    @Override
    public List<Student> getAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, studentRowMapper);
    }

    @Override
    public Optional<Student> getById(Integer id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, studentRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Integer id) {}

    @Override
    public void update(Student item) {
        jdbcTemplate.update(
                SQL_UPDATE_STUDENT,
                item.getPhotoId(),
                item.getTgUsername(),
                item.getId()
        );
    }

    @Override
    public Optional<Student> getByEmail(String email) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, studentRowMapper, email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Subscriber> getSubscribersByTeacherId(Integer id) {
        List<Subscriber> subscribers = jdbcTemplate.query(SQL_SELECT_SUBSCRIBERS_BY_TEACHER_ID, subscriberRowMapper, id);
        System.out.println(subscribers);
        return subscribers;
    }
}
