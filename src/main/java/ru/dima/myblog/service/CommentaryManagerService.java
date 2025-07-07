package ru.dima.myblog.service;

import ru.dima.myblog.model.Commentary;

public interface CommentaryManagerService {

    void createCommentary(long id, Commentary commentary);
}
