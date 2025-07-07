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
    private final TagManagerDao tagManagerDao;

    @Autowired
    public PostManagerDaoImpl(JdbcTemplate jdbcTemplate, TagManagerDao tagManagerDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.tagManagerDao = tagManagerDao;
    }

    @Override
    public List<Post> findAll() {
        return jdbcTemplate.query("SELECT * FROM posts", (rs, rowNum) -> {
            Post post = new Post();
            long postId = rs.getLong("id");
            post.setId(postId);
            post.setHeading(rs.getString("heading"));
            post.setBody(rs.getString("body"));
            post.setImage(rs.getBlob("image"));
            post.setLikes(rs.getLong("likes"));
            post.setTags(tagManagerDao.findAllTagsToPost(postId));
            return post;
        });
    }

    @Override
    public Optional<Post> findById(long id) {

        return jdbcTemplate.query("SELECT * FROM posts WHERE id=?",
                        new Object[]{id}, (rs, rowNum) -> {
            Post post = new Post();
            post.setId(rs.getLong("id"));
            post.setHeading(rs.getString("heading"));
            post.setBody(rs.getString("body"));
            post.setImage(rs.getBlob("image"));
            post.setLikes(rs.getLong("likes"));
            post.setTags(tagManagerDao.findAllTagsToPost(id));
            return post;
        })
                .stream()
                .findAny();
    }

    @Override
    public void create(Post post) {
        jdbcTemplate.update("INSERT INTO posts (heading, body, image, likes) VALUES (?, ?, ?, ?)",
                post.getHeading(), post.getBody(), post.getImage(), 0);
    }

    @Override
    public void update(long id, Post updatedPost) {
        jdbcTemplate.update("UPDATE posts SET heading=?, body=?, image=? WHERE id=?",
                updatedPost.getHeading(), updatedPost.getBody(), updatedPost.getImage(), id);
    }

    @Override
    public void deleteById(long id) {
        jdbcTemplate.update("DELETE FROM posts WHERE id=?", id);
    }
}
