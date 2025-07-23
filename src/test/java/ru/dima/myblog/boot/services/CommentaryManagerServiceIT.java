package ru.dima.myblog.boot.services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.dima.myblog.boot.dao.CommentaryManagerDao;
import ru.dima.myblog.boot.dao.PostManagerDao;
import ru.dima.myblog.boot.models.Commentary;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
public class CommentaryManagerServiceIT {

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
        long postId = 10;
        Commentary commentary = new Commentary();
        commentary.setLocalDateTime(LocalDateTime.now());
        commentary.setText("newCommentary");

        commentaryManagerService.createCommentary(postId, commentary);
        long autoAssignedId = 2;

        assertNotNull(commentary.getLocalDateTime());
        Optional<Commentary> retrieved = commentaryManagerDao.findCommentaryByPostAndCommentaryId(postId, autoAssignedId);
        assertFalse(retrieved.isPresent());
    }

    @Test
    void testFindCommentaryByPostAndCommentaryId() {
        long postId = 2;
        long commentaryId = 2;

        Optional<Commentary> result = commentaryManagerService.findCommentaryByPostAndCommentaryId(postId, commentaryId);

        assertFalse(result.isPresent());
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
        long postId = 10;
        long commentaryId = 0;

        commentaryManagerService.deleteCommentary(postId, commentaryId);

        Optional<Commentary> result = commentaryManagerDao.findCommentaryByPostAndCommentaryId(postId, commentaryId);
        assertFalse(result.isPresent());
    }
}
