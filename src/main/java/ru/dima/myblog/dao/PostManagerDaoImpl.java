package ru.dima.myblog.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.dima.myblog.model.Post;

import java.util.List;
import java.util.Optional;

@Repository
public class PostManagerDaoImpl implements PostManagerDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostManagerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Post> findAll() {
        return jdbcTemplate.query("SELECT * FROM posts",
                new BeanPropertyRowMapper<>(Post.class));
    }

    @Override
    public Optional<Post> findById(long id) {
        String sql = "SELECT * FROM posts WHERE id = ?";
        Object[] longId = new Object[]{id};
        RowMapper<Post> mapToPostClass = new BeanPropertyRowMapper<>(Post.class);

        Optional<Post> result = jdbcTemplate.query(sql, longId, mapToPostClass)
                .stream()
                .findAny();

        return result;
    }

//    @Override
//    public void create(Post post) {
//
//    }
//
//    @Override
//    public void update(Post post) {
//
//    }
//
//    @Override
//    public void deleteById(long id) {
//
//    }
}
