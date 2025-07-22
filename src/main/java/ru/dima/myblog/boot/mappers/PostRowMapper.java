package ru.dima.myblog.boot.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dima.myblog.boot.dao.CommentaryManagerDao;
import ru.dima.myblog.boot.dao.TagManagerDao;
import ru.dima.myblog.boot.model.Post;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PostRowMapper {

    private static TagManagerDao tagManagerDao;
    private static CommentaryManagerDao commentaryManagerDao;

    @Autowired
    public PostRowMapper(TagManagerDao tagManagerDao, CommentaryManagerDao commentaryManagerDao) {
        this.tagManagerDao = tagManagerDao;
        this.commentaryManagerDao = commentaryManagerDao;
    }

    public PostRowMapper(){}

    public static Post mapRowToPost(ResultSet rs, int rowNum) throws SQLException {
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
    }

    public Post mapRowToPostWithDaos(ResultSet rs, int rowNum,
                                     TagManagerDao tagDao,
                                     CommentaryManagerDao commentaryDao) throws SQLException {
        Post post = new Post();
        long postId = rs.getLong("id");
        post.setId(postId);
        post.setHeading(rs.getString("heading"));
        post.setBody(rs.getString("body"));
        post.setImage(rs.getBytes("image"));
        post.setLikes(rs.getLong("likes"));
        post.setTags(tagDao.findAllTagsToPost(postId));
        post.setCommentaries(commentaryDao.findAllCommentaries(postId));
        post.setLocalDateTime(rs.getTimestamp("creation_timestamp").toLocalDateTime());
        return post;
    }
}
