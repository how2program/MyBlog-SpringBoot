package ru.dima.myblog.boot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dima.myblog.boot.dao.LikeableDao;

@Service
public class LikeHandler implements Likeable {

    private final LikeableDao likeHandlerDao;

    @Autowired
    public LikeHandler(LikeableDao likeHandlerDao) {
        this.likeHandlerDao = likeHandlerDao;
    }

    @Override
    public void like(long id) {
        likeHandlerDao.like(id);
    }
}
