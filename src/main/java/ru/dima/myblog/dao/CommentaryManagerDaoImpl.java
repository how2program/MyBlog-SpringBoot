package ru.dima.myblog.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.dima.myblog.model.Commentary;
import ru.dima.myblog.model.Post;

import java.util.List;

@Repository
public class CommentaryManagerDaoImpl implements CommentaryManagerDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentaryManagerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createCommentary(long postId, Commentary commentary) {
        jdbcTemplate.update("INSERT INTO commentaries(post_id, text) VALUES (?, ?)",
                postId, commentary.getText());
    }

    @Override
    public List<Commentary> findAllCommentaries(long postId) {
        return jdbcTemplate.query("SELECT * FROM commentaries WHERE post_id=?", (rs, rowNum) -> {
            Commentary commentary = new Commentary();
            commentary.setId(rs.getLong("id"));
            commentary.setPostId(rs.getLong("post_id"));
            commentary.setText(rs.getString("text"));
            return commentary;
    }, postId);



    }
}
