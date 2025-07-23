package ru.dima.myblog.boot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.dima.myblog.boot.models.Commentary;

import java.util.List;
import java.util.Optional;

@Repository
public class CommentaryManagerDaoImpl implements CommentaryManagerDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentaryManagerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createCommentary(long postId, Commentary commentary) {
        jdbcTemplate.update("INSERT INTO commentaries(post_id, text, creation_timestamp) VALUES (?, ?, ?)",
                postId, commentary.getText(), commentary.getLocalDateTime());
    }

    @Override
    public List<Commentary> findAllCommentaries(long postId) {
        return jdbcTemplate.query("SELECT * FROM commentaries WHERE post_id=?", (rs, rowNum) -> {
            Commentary commentary = new Commentary();
            commentary.setId(rs.getLong("id"));
            commentary.setPostId(rs.getLong("post_id"));
            commentary.setText(rs.getString("text"));
            commentary.setLocalDateTime(rs.getTimestamp("creation_timestamp").toLocalDateTime());
            return commentary;
    }, postId);
    }

    @Override
    public Optional<Commentary> findCommentaryByPostAndCommentaryId(long postId, long commentaryId) {
        return jdbcTemplate.query("SELECT * FROM commentaries WHERE post_id=? AND id=?",
                new Object[]{postId, commentaryId}, (rs, rowNum) -> {
            Commentary commentary = new Commentary();
            commentary.setId(rs.getLong("id"));
            commentary.setPostId(rs.getLong("post_id"));
            commentary.setText(rs.getString("text"));
            commentary.setLocalDateTime(rs.getTimestamp("creation_timestamp").toLocalDateTime());
            return commentary;
                })
                .stream()
                .findAny();
    }

    @Override
    public void updateCommentary(long postId, long commentaryId, Commentary updatedCommentary) {
        jdbcTemplate.update("UPDATE commentaries SET text=? WHERE post_id=? AND id=?",
                updatedCommentary.getText(), postId, commentaryId);
    }

    @Override
    public void deleteCommentary(long postId, long commentaryId) {
        jdbcTemplate.update("DELETE FROM commentaries WHERE post_id=? AND id=?", postId, commentaryId);
    }

}
