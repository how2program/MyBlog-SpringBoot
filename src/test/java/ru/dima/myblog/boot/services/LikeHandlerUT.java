package ru.dima.myblog.boot.services;

import org.junit.jupiter.api.Test;
import ru.dima.myblog.boot.dao.LikeableDao;

import static org.mockito.Mockito.*;

public class LikeHandlerUT {
    @Test
    void testLikeMethod() {
        LikeableDao mockLikeableDao = mock(LikeableDao.class);

        LikeHandler likeHandler = new LikeHandler(mockLikeableDao);

        long testId = 123L;

        likeHandler.like(testId);

        verify(mockLikeableDao).like(testId);
    }
}
