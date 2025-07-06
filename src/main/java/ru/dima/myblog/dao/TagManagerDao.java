package ru.dima.myblog.dao;

import ru.dima.myblog.model.Tag;

import java.util.List;
import java.util.Optional;

public interface TagManagerDao {

    List<Tag> findAll();

    Optional<Tag> findById(long id);

    void create(Tag post);

    void deleteById(long id);

}
