package ru.dima.myblog.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import ru.dima.myblog.dao.CommentaryManagerDao;
import ru.dima.myblog.model.Commentary;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CommentaryManagerServiceTestUnit {

    private CommentaryManagerDao commentaryManagerDao;
    private CommentaryManagerServiceImpl service;

    @BeforeEach
    void setUp() {
        commentaryManagerDao = mock(CommentaryManagerDao.class);
        service = new CommentaryManagerServiceImpl(commentaryManagerDao);
    }

    @Test
    void testCreateCommentary() {
        long postId = 1;
        Commentary commentary = new Commentary();
        service.createCommentary(postId, commentary);
        assertNotNull(commentary.getLocalDateTime());
        verify(commentaryManagerDao).createCommentary(postId, commentary);
    }

    @Test
    void testFindCommentaryByPostAndCommentaryId_Found() {
        long postId = 2;
        long commentaryId = 3;
        Commentary commentary = new Commentary();
        when(commentaryManagerDao.findCommentaryByPostAndCommentaryId(postId, commentaryId))
                .thenReturn(Optional.of(commentary));

        Optional<Commentary> result = service.findCommentaryByPostAndCommentaryId(postId, commentaryId);

        assertTrue(result.isPresent());
        assertEquals(commentary, result.get());
        verify(commentaryManagerDao).findCommentaryByPostAndCommentaryId(postId, commentaryId);
    }

    @Test
    void testFindCommentaryByPostAndCommentaryId_NotFound() {
        long postId = 4;
        long commentaryId = 5;
        when(commentaryManagerDao.findCommentaryByPostAndCommentaryId(postId, commentaryId))
                .thenReturn(Optional.empty());

        Optional<Commentary> result = service.findCommentaryByPostAndCommentaryId(postId, commentaryId);

        assertFalse(result.isPresent());
        verify(commentaryManagerDao).findCommentaryByPostAndCommentaryId(postId, commentaryId);
    }

    @Test
    void testUpdateCommentary() {
        long postId = 10;
        long commentaryId = 20;
        Commentary updatedCommentary = new Commentary();

        service.updateCommentary(postId, commentaryId, updatedCommentary);

        verify(commentaryManagerDao).updateCommentary(postId, commentaryId, updatedCommentary);
    }

    @Test
    void testDeleteCommentary() {
        long postId = 7;
        long commentaryId = 8;

        service.deleteCommentary(postId, commentaryId);

        verify(commentaryManagerDao).deleteCommentary(postId, commentaryId);
    }
}
