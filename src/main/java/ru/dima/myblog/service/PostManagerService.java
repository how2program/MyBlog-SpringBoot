package ru.dima.myblog.service;

import org.springframework.web.multipart.MultipartFile;
import ru.dima.myblog.model.Post;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PostManagerService {

    List<Post> findAll();

    Optional<Post> findById(long id);

    long create(Post post);

    void update(long id, Post updatedPost);

    void deleteById(long id);

}
