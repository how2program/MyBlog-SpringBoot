package ru.dima.myblog.boot.mappers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import ru.dima.myblog.boot.dao.CommentaryManagerDao;
import ru.dima.myblog.boot.dao.TagManagerDao;
import ru.dima.myblog.boot.models.Commentary;
import ru.dima.myblog.boot.models.Post;
import ru.dima.myblog.boot.models.Tag;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
public class MapperTest {

    @Test
    public void testMapRowToPost() throws SQLException {

        ResultSet rs = Mockito.mock(ResultSet.class);

        Mockito.when(rs.getLong("id")).thenReturn(1L);
        Mockito.when(rs.getString("heading")).thenReturn("Заголовок");
        Mockito.when(rs.getString("body")).thenReturn("Тело поста");
        Mockito.when(rs.getBytes("image")).thenReturn(new byte[]{1, 2, 3});
        Mockito.when(rs.getLong("likes")).thenReturn(42L);
        Mockito.when(rs.getTimestamp("creation_timestamp"))
                .thenReturn(Timestamp.valueOf(LocalDateTime.of(2023, 10, 1, 12, 0)));


        TagManagerDao tagDao = Mockito.mock(TagManagerDao.class);
        CommentaryManagerDao commentaryDao = Mockito.mock(CommentaryManagerDao.class);

        Tag tag1 = new Tag();
        tag1.setTag("tag1");
        Tag tag2 = new Tag();
        tag2.setTag("tag2");

        Commentary commentary = new Commentary();
        commentary.setText("commentary");

        List<Tag> tags = Arrays.asList(tag1, tag2);
        List<Commentary> commentaries = List.of(commentary);

        Mockito.when(tagDao.findAllTagsToPost(1)).thenReturn(tags);
        Mockito.when(commentaryDao.findAllCommentaries(1)).thenReturn(commentaries);

        Post post = new PostRowMapper().mapRowToPostWithDaos(rs, 0, tagDao, commentaryDao);

        assertEquals(1, post.getId());
        assertEquals("Заголовок", post.getHeading());
        assertEquals("Тело поста", post.getBody());
        assertArrayEquals(new byte[]{1, 2, 3}, post.getImage());
        assertEquals(42, post.getLikes());
        assertEquals(LocalDateTime.of(2023, 10, 1, 12, 0), post.getLocalDateTime());

        assertEquals(tags, post.getTags());
        assertEquals(commentaries, post.getCommentaries());
    }
}
