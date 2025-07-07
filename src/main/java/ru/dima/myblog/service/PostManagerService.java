package ru.dima.myblog.service;

import ru.dima.myblog.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostManagerService {

    List<Post> findAll();

    Optional<Post> findById(long id);

    void create(Post post);

    void update(long id, Post updatedPost);

    void deleteById(long id);

}
