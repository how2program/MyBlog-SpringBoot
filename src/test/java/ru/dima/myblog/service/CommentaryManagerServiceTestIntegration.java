package ru.dima.myblog.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.dima.myblog.config.DataSourceConfiguration;
import ru.dima.myblog.config.ServletConfiguration;
import ru.dima.myblog.config.ThymeleafConfiguration;
import ru.dima.myblog.dao.CommentaryManagerDao;
import ru.dima.myblog.dao.PostManagerDao;
import ru.dima.myblog.model.Commentary;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ServletConfiguration.class, DataSourceConfiguration.class, ThymeleafConfiguration.class})
@WebAppConfiguration
public class CommentaryManagerServiceTestIntegration {

    @Autowired
    private CommentaryManagerService commentaryManagerService;

    @Autowired
    private CommentaryManagerDao commentaryManagerDao;

    @BeforeEach
    void setUp() {
        Commentary commentary = new Commentary();
        commentary.setLocalDateTime(LocalDateTime.now());
        commentary.setText("commentary");
        long forFirstPost = 1;
        commentaryManagerDao.createCommentary(forFirstPost, commentary);
    }

    @Test
    void testCreateCommentary() {
        long postId = 2;
        Commentary commentary = new Commentary();
        commentary.setLocalDateTime(LocalDateTime.now());
        commentary.setText("newCommentary");

        commentaryManagerService.createCommentary(postId, commentary);
        long autoAssignedId = 2;

        assertNotNull(commentary.getLocalDateTime());
        Optional<Commentary> retrieved = commentaryManagerDao.findCommentaryByPostAndCommentaryId(postId, autoAssignedId);
        assertTrue(retrieved.isPresent());
        assertEquals("newCommentary", retrieved.get().getText());
    }

    @Test
    void testFindCommentaryByPostAndCommentaryId() {
        long postId = 1;
        long commentaryId = 1;

        Optional<Commentary> result = commentaryManagerService.findCommentaryByPostAndCommentaryId(postId, commentaryId);

        assertTrue(result.isPresent());
        assertEquals("commentary", result.get().getText());
    }

    @Test
    void testUpdateCommentary() {
        long postId = 1;
        long commentaryId = 1;

        Commentary updated = new Commentary();
        updated.setText("updatedCommentary");
        commentaryManagerService.updateCommentary(postId, commentaryId, updated);

        Optional<Commentary> result = commentaryManagerDao.findCommentaryByPostAndCommentaryId(postId, commentaryId);
        assertTrue(result.isPresent());
        assertEquals("updatedCommentary", result.get().getText());
    }

    @Test
    void testDeleteCommentary() {
        long postId = 1;
        long commentaryId = 1;

        commentaryManagerService.deleteCommentary(postId, commentaryId);

        Optional<Commentary> result = commentaryManagerDao.findCommentaryByPostAndCommentaryId(postId, commentaryId);
        assertFalse(result.isPresent());
    }
}
