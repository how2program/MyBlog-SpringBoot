package ru.dima.myblog.dao;

import ru.dima.myblog.model.Commentary;

import java.util.List;
import java.util.Optional;

public interface CommentaryManagerDao {

    List<Commentary> findAll();

    Optional<Commentary> findById(long id);

    void create(Commentary post);

    void update(Commentary post);

    void deleteById(long id);

}
