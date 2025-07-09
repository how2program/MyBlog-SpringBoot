package ru.dima.myblog.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.dima.myblog.model.Post;

import java.util.List;

@Repository
public class PaginatorDao {

    JdbcTemplate jdbcTemplate;
    private final TagManagerDao tagManagerDao;
    private final CommentaryManagerDao commentaryManagerDao;

    @Autowired
    public PaginatorDao(JdbcTemplate jdbcTemplate, TagManagerDao tagManagerDao, CommentaryManagerDao commentaryManagerDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.tagManagerDao = tagManagerDao;
        this.commentaryManagerDao = commentaryManagerDao;
    }

    public List<Post> getAllPostsByPage(int offset, int limit) {
        return jdbcTemplate.query("SELECT * FROM posts ORDER BY id LIMIT ? OFFSET ?",
                new Object[]{limit, offset}, (rs, rowNum) -> {
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
