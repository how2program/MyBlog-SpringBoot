package ru.dima.myblog.service;

import ru.dima.myblog.model.Post;
import ru.dima.myblog.model.Tag;

import java.util.List;
import java.util.Optional;

public interface TagManagerService {

//    List<Tag> findAllTagsToPost(long postId);

    void create(String post, long currentPostId);
}
