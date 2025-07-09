package ru.dima.myblog.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import ru.dima.myblog.model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;


@Repository
public class PostManagerDaoImpl implements PostManagerDao {

    private final JdbcTemplate jdbcTemplate;
    private final TagManagerDao tagManagerDao;
    private final CommentaryManagerDao commentaryManagerDao;
    private KeyHolder keyHolder;

    @Autowired
    public PostManagerDaoImpl(JdbcTemplate jdbcTemplate,
                              TagManagerDao tagManagerDao,
                              CommentaryManagerDao commentaryManagerDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.tagManagerDao = tagManagerDao;
        this.commentaryManagerDao = commentaryManagerDao;
    }

    @Override
    public List<Post> findAll() {
        return jdbcTemplate.query("SELECT * FROM posts", (rs, rowNum) -> {
            Post post = new Post();
            long postId = rs.getLong("id");
            post.setId(postId);
            post.setHeading(rs.getString("heading"));
            post.setBody(rs.getString("body"));
            post.setImage(rs.getBytes("image"));
            post.setLikes(rs.getLong("likes"));
            post.setTags(tagManagerDao.findAllTagsToPost(postId));
            post.setCommentaries(commentaryManagerDao.findAllCommentaries(postId));
            post.setLocalDateTime(rs.getTimestamp("creation_timestamp").toLocalDateTime());
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
            post.setImage(rs.getBytes("image"));
            post.setLikes(rs.getLong("likes"));
            post.setTags(tagManagerDao.findAllTagsToPost(id));
            post.setCommentaries(commentaryManagerDao.findAllCommentaries(id));
            post.setLocalDateTime(rs.getTimestamp("creation_timestamp").toLocalDateTime());
            return post;
        })
                .stream()
                .findAny();
    }

    @Override
    public long create(Post post) {
        keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO posts (heading, body, image, likes, creation_timestamp) VALUES (?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, post.getHeading());
                ps.setString(2, post.getBody());
                ps.setBytes(3, post.getImage());
                ps.setLong(4, 0);
                ps.setTimestamp(5, java.sql.Timestamp.valueOf(post.getLocalDateTime()));
                return ps;
            }
        }, keyHolder);
        Long generatedId = keyHolder.getKey().longValue();
        return generatedId;
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

    @Override
    public List<Post> findByTag(String tag) {
        return jdbcTemplate.query("SELECT posts.* FROM posts JOIN tags ON posts.id = tags.post_id WHERE tags.tag = ?",
                new Object[]{tag}, (rs, rowNum) -> {
                    Post post = new Post();
                    long postId = rs.getLong("id");
                    post.setId(postId);
                    post.setHeading(rs.getString("heading"));
                    post.setBody(rs.getString("body"));
                    post.setImage(rs.getBytes("image"));
                    post.setLikes(rs.getLong("likes"));
                    post.setTags(tagManagerDao.findAllTagsToPost(postId));
                    post.setCommentaries(commentaryManagerDao.findAllCommentaries(postId));
                    post.setLocalDateTime(rs.getTimestamp("creation_timestamp").toLocalDateTime());
                    return post;
                });
    }

}
