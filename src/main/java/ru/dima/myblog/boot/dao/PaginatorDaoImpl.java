package ru.dima.myblog.boot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.dima.myblog.boot.mappers.PostRowMapper;
import ru.dima.myblog.boot.models.Post;

import java.util.List;

@Repository
public class PaginatorDaoImpl implements PaginatorDao {

    JdbcTemplate jdbcTemplate;
    private final TagManagerDao tagManagerDao;
    private final CommentaryManagerDao commentaryManagerDao;

    @Autowired
    public PaginatorDaoImpl(JdbcTemplate jdbcTemplate, TagManagerDao tagManagerDao, CommentaryManagerDao commentaryManagerDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.tagManagerDao = tagManagerDao;
        this.commentaryManagerDao = commentaryManagerDao;
    }

    @Override
    public List<Post> findPostsPage(int offset, int limit) {
        String sql = "SELECT * FROM posts ORDER BY creation_timestamp LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, new Object[]{limit, offset}, PostRowMapper::mapRowToPost);
    }

    @Override
    public long countPosts() {
        String sql = "SELECT COUNT(*) FROM posts";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }
}
