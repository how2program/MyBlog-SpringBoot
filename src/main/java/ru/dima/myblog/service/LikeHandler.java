package ru.dima.myblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dima.myblog.dao.LikeableDao;
import ru.dima.myblog.dao.PostManagerDao;
import ru.dima.myblog.model.Post;

@Service
public class LikeHandler implements Likeable {

    LikeableDao likeHandlerDao;

    @Autowired
    public LikeHandler(LikeableDao likeHandlerDao) {
        this.likeHandlerDao = likeHandlerDao;
    }

    @Override
    public void like(long id) {
        likeHandlerDao.like(id);
    }
}
