package ru.dima.myblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dima.myblog.dao.CommentaryManagerDao;
import ru.dima.myblog.model.Commentary;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentaryManagerServiceImpl implements CommentaryManagerService {

    private final CommentaryManagerDao commentaryManagerDao;

    @Autowired
    public CommentaryManagerServiceImpl(CommentaryManagerDao commentaryManagerDao) {
        this.commentaryManagerDao = commentaryManagerDao;
    }

    @Override
    public void createCommentary(long postId, Commentary commentary) {
        commentary.setLocalDateTime(LocalDateTime.now());
        commentaryManagerDao.createCommentary(postId, commentary);
    }

    @Override
    public Optional<Commentary> findCommentaryByPostAndCommentaryId(long postId, long commentaryId) {
        return commentaryManagerDao.findCommentaryByPostAndCommentaryId(postId, commentaryId);
    }

    @Override
    public void updateCommentary(long postId, long commentaryId, Commentary updatedCommentary) {
        commentaryManagerDao.updateCommentary(postId, commentaryId, updatedCommentary);
    }
}
