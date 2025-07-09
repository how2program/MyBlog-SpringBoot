package ru.dima.myblog.service;

import org.junit.jupiter.api.Test;
import ru.dima.myblog.dao.LikeableDao;

import static org.mockito.Mockito.*;

public class LikeHandlerTestUnit {

    @Test
    void testLikeMethod() {
        LikeableDao mockLikeableDao = mock(LikeableDao.class);

        LikeHandler likeHandler = new LikeHandler(mockLikeableDao);

        long testId = 123L;

        likeHandler.like(testId);

        verify(mockLikeableDao).like(testId);
    }

}
