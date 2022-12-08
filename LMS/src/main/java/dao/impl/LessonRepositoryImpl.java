package dao.impl;

import dao.LessonRepository;
import dto.FavouriteLesson;
import model.Lesson;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class LessonRepositoryImpl implements LessonRepository {

    private final JdbcTemplate jdbcTemplate;

    private final static String SQL_INSERT = "insert into lesson(title, description, file_id, teacher_id, video_link ) VALUES (?, ?, ?, ?, ?);";
    private final static String SQL_SELECT_ALL_BY_TEACHER_ID = "select * from lesson where teacher_id = ?;";
    private final static String SQL_SELECT_BY_ID = "select * from lesson where id = ?;";
    private final static String SQL_SELECT_ALL = "select * from lesson;";
    private final static String SQL_INSERT_FAVOURITE_LESSON = "insert into favourite_lesson(lesson_id, student_id) VALUES (?, ?);";
    private final static String SQL_DELETE_FAVOURITE_LESSON = "delete from favourite_lesson where id = ?;";
    private final static String SQL_SELECT_ALL_FAVOURITE_LESSON_BY_STUDENT_ID = "select lesson_id as id, title, description, file_id, video_link, teacher_id from lesson join favourite_lesson fl on lesson.id = fl.lesson_id where fl.student_id = ?;";
    private final static String SQL_SELECT_FAVOURITE_LESSON = "select * from favourite_lesson where lesson_id = ? and student_id = ?;";


    private final RowMapper<Lesson> lessonRowMapper = (row, rowNumber) -> Lesson.builder()
            .id(row.getInt("id"))
            .title(row.getString("title"))
            .description(row.getString("description"))
            .fileId(row.getInt("file_id"))
            .videoLink(row.getString("video_link"))
            .teacherId(row.getInt("teacher_id"))
            .build();

    private final RowMapper<FavouriteLesson> favouriteLessonRowMapper = (row, rowNumber) -> FavouriteLesson.builder()
            .id(row.getInt("id"))
            .lessonId(row.getInt("lesson_id"))
            .studentId(row.getInt("student_id"))
            .build();

    public LessonRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Lesson save(Lesson item) {
        if (item.getId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"id"});
                statement.setString(1, item.getTitle());
                statement.setString(2, item.getDescription());
                statement.setInt(3, item.getFileId());
                statement.setInt(4, item.getTeacherId());
                statement.setString(5, String.valueOf(item.getVideoLink()));
                return statement;
            }, keyHolder);
            item.setId(keyHolder.getKey().intValue());
        }
        return item;
    }

    @Override
    public List<Lesson> getLessonsByTeacherId(Integer id) {
        return jdbcTemplate.query(SQL_SELECT_ALL_BY_TEACHER_ID, lessonRowMapper, id);
    }

    @Override
    public FavouriteLesson saveFavouriteLesson(FavouriteLesson favouriteLesson) {
        if (favouriteLesson.getId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT_FAVOURITE_LESSON, new String[]{"id"});
                statement.setInt(1, favouriteLesson.getLessonId());
                statement.setInt(2, favouriteLesson.getStudentId());
                return statement;
            }, keyHolder);
            favouriteLesson.setId(keyHolder.getKey().intValue());
        }
        return favouriteLesson;
    }

    @Override
    public void deleteFavouriteLesson(Integer id) {
        jdbcTemplate.update(SQL_DELETE_FAVOURITE_LESSON, id);
    }

    @Override
    public List<Lesson> getFavouritesLessonsByStudentId(Integer id) {
        List<Lesson> lessonList = jdbcTemplate.query(SQL_SELECT_ALL_FAVOURITE_LESSON_BY_STUDENT_ID, lessonRowMapper, id);
        System.out.println(lessonList);
        return lessonList;
    }

    @Override
    public Boolean isFavourite(Integer lessonId, Integer studentId) {
        return jdbcTemplate.queryForRowSet(SQL_SELECT_FAVOURITE_LESSON, lessonId, studentId).next();

    }

    @Override
    public Optional<FavouriteLesson> getFavouriteLesson(Integer lessonId, Integer studentId) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(
                            SQL_SELECT_FAVOURITE_LESSON,
                            favouriteLessonRowMapper ,
                            lessonId,
                            studentId
                    )
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Lesson> getAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, lessonRowMapper);
    }

    @Override
    public Optional<Lesson> getById(Integer id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, lessonRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Integer id) {}

    @Override
    public void update(Lesson item) {}
}

