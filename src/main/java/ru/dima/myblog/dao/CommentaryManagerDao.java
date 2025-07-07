package ru.dima.myblog.dao;

import ru.dima.myblog.model.Commentary;

import java.util.List;

public interface CommentaryManagerDao {

    void createCommentary(long postId, Commentary commentary);

    List<Commentary> findAllCommentaries(long postId);

}
