package ru.dima.myblog.dao;

import ru.dima.myblog.model.Commentary;

import java.util.List;
import java.util.Optional;

public interface CommentaryManagerDao {

    void createCommentary(long id, Commentary commentary);

}
