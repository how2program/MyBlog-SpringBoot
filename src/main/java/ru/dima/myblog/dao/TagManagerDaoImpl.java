package ru.dima.myblog.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.dima.myblog.model.Post;
import ru.dima.myblog.model.Tag;

import java.util.List;

@Repository
public class TagManagerDaoImpl implements TagManagerDao {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public TagManagerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Tag> findAllTagsToPost(long postId) {
        return jdbcTemplate.query("SELECT * FROM tags WHERE post_id=?", (rs, rowNum) ->{
            Tag tag = new Tag();
            tag.setId(rs.getLong("id"));
            tag.setPostId(rs.getLong("post_id"));
            tag.setTag(rs.getString("tag"));
            return tag;
        }, postId);
    }

    @Override
    public void create(List<Tag> tags) {
        for (Tag tag : tags) {
            jdbcTemplate.update("INSERT INTO tags (post_id, tag) VALUES (?, ?)",
                    tag.getPostId(), tag.getTag());
        }
    }
}
