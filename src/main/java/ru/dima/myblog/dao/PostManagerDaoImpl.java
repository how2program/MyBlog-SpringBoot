package ru.dima.myblog.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.dima.myblog.model.Post;

import java.sql.ResultSet;
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
        return jdbcTemplate.query("SELECT * FROM posts", (rs, rowNum) -> {
            Post post = new Post();
            post.setId(rs.getLong("id"));
            post.setHeading(rs.getString("heading"));
            post.setBody(rs.getString("body"));
//            post.setImage(rs.getBlob("image"));
//            post.setLikes(rs.getLong("likes"));
            return post;
        });
    }

    @Override
    public Optional<Post> findById(long id) {
//        RowMapper<Post> mapToPostClass = new BeanPropertyRowMapper<>(Post.class);

        return jdbcTemplate.query("SELECT * FROM posts WHERE id = ?",
                        new Object[]{id}, (rs, rowNum) -> {
            Post post = new Post();
            post.setId(rs.getLong("id"));
            post.setHeading(rs.getString("heading"));
            post.setBody(rs.getString("body"));
//            post.setImage(rs.getBlob("image"));
//            post.setLikes(rs.getLong("likes"));
            return post;
        })
                .stream()
                .findAny();
    }

    @Override
    public void create(Post post) {
        jdbcTemplate.update("INSERT INTO posts (heading, body/*, image*/) VALUES (?, ? /*,?*/)",
                post.getHeading(), post.getBody() /*, post.getImage()*/);
    }
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
