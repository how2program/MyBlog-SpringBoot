package ru.dima.myblog.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.dima.myblog.model.Commentary;

@Repository
public class CommentaryManagerDaoImpl implements CommentaryManagerDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentaryManagerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createCommentary(long id, Commentary commentary) {
    }
}
