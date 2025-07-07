package ru.dima.myblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dima.myblog.dao.CommentaryManagerDao;
import ru.dima.myblog.model.Commentary;

@Service
public class CommentaryManagerServiceImpl implements CommentaryManagerService {

    private final CommentaryManagerDao commentaryManagerDao;

    @Autowired
    public CommentaryManagerServiceImpl(CommentaryManagerDao commentaryManagerDao) {
        this.commentaryManagerDao = commentaryManagerDao;
    }

    @Override
    public void createCommentary(long postId, Commentary commentary) {
        commentaryManagerDao.createCommentary(postId, commentary);
    }
}
