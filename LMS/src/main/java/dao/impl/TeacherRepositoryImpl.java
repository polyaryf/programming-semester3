package dao.impl;

import dao.TeacherRepository;
import model.Status;
import model.Teacher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class TeacherRepositoryImpl implements TeacherRepository {
    private final JdbcTemplate jdbcTemplate;

    private final static String SQL_SELECT_ALL = "select * from teacher;";
    private final static String SQL_INSERT = "insert into teacher(first_name, last_name, email, password, status) VALUES (?, ?, ?, ?, ?);";
    private final static String SQL_SELECT_BY_ID = "select * from teacher where id = ?;";
    private final static String SQL_SELECT_BY_EMAIL = "select * from teacher where email = ?;";
    private final static String SQL_UPDATE_TEACHER = "update teacher set photo_id = ?, description = ?, work_experience = ?, tg_username = ? where id = ?";

    private final RowMapper<Teacher> teacherRowMapper = (row, rowNumber) -> Teacher.builder()
            .id(row.getInt("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .email(row.getString("email"))
            .status(Status.valueOf(row.getString("status")))
            .password(row.getString("password"))
            .photoId(row.getInt("photo_id"))
            .description(row.getString("description"))
            .workExperience(row.getInt("work_experience"))
            .tgUsername(row.getString("tg_username"))
            .build();

    public TeacherRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Teacher save(Teacher item) {
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
        }
        return item;
    }

    @Override
    public List<Teacher> getAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, teacherRowMapper);
    }

    @Override
    public Optional<Teacher> getById(Integer id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, teacherRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Integer id) { }

    @Override
    public void update(Teacher item) {
        jdbcTemplate.update(
                SQL_UPDATE_TEACHER,
                item.getPhotoId(),
                item.getDescription(),
                item.getWorkExperience(),
                item.getTgUsername(),
                item.getId()
        );
    }

    @Override
    public Optional<Teacher> getByEmail(String email) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, teacherRowMapper, email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
