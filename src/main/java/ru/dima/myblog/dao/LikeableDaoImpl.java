package ru.dima.myblog.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.dima.myblog.model.Post;

@Repository
public class LikeableDaoImpl implements LikeableDao {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public LikeableDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void like(Post post) {
        jdbcTemplate.update("UPDATE posts SET likes = ? WHERE id = ?", post.getLikes(), post.getId());

    }
}
