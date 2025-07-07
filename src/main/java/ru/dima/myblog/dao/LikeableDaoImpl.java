package ru.dima.myblog.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.dima.myblog.model.Post;

import java.util.Optional;

@Repository
public class LikeableDaoImpl implements LikeableDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LikeableDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void like(long id) {
        long likesOnId = findLikesById(id);
        likesOnId++;
        jdbcTemplate.update("UPDATE posts SET likes = ? WHERE id = ?", likesOnId, id);

    }

    public  long findLikesById(long id) {

        Post currentPost = jdbcTemplate.query("SELECT likes FROM posts WHERE id = ?",
                        new Object[]{id}, (rs, rowNum) -> {
                            Post post = new Post();
                            post.setLikes(rs.getLong("likes"));
                            return post;
                        }).stream().findAny().orElse(null);

        return currentPost.getLikes();
    }
}
