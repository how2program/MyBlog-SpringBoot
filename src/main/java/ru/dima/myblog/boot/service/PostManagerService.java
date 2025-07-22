package ru.dima.myblog.boot.service;

import ru.dima.myblog.boot.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostManagerService {

    List<Post> findAll();

    Optional<Post> findById(long id);

    long create(Post post);

    void update(long id, Post updatedPost);

    void deleteById(long id);

    List<Post> findByTag(String tag, int offset, int limit);
}
