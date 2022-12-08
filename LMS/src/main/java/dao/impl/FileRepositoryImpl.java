package dao.impl;

import dao.FileRepository;
import model.FileInfo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class FileRepositoryImpl implements FileRepository {
    private final JdbcTemplate jdbcTemplate;

    private final static String SQL_INSERT = "insert into file_info(original_file_name, storage_file_name, size, type) " + "values (?, ?, ?, ?)";
    private final static String SQL_SELECT_BY_ID = "select * from file_info where id = ?";

    private final RowMapper<FileInfo> rowMapper = (row, rowNumber) -> FileInfo.builder()
            .id(row.getInt("id"))
            .originalFileName(row.getString("original_file_name"))
            .storageFileName(row.getString("storage_file_name"))
            .size(row.getLong("size")).type(row.getString("type"))
            .build();

    public FileRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public FileInfo save(FileInfo item) {
        if (item.getId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"id"});
                statement.setString(1, item.getOriginalFileName());
                statement.setString(2, item.getStorageFileName());
                statement.setLong(3, item.getSize());
                statement.setString(4, item.getType());
                return statement;
            }, keyHolder);
            item.setId(keyHolder.getKey().intValue());
        }
        return item;
    }

    @Override
    public List<FileInfo> getAll() {
        return null;
    }

    @Override
    public Optional<FileInfo> getById(Integer id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, rowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Integer id) {}

    @Override
    public void update(FileInfo item) {}
}
