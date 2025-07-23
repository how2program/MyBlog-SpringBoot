package ru.dima.myblog.boot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dima.myblog.boot.dao.CommentaryManagerDao;
import ru.dima.myblog.boot.models.Commentary;

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

    @Override
    public void deleteCommentary(long postId, long commentaryId) {
        commentaryManagerDao.deleteCommentary(postId, commentaryId);
    }
}
