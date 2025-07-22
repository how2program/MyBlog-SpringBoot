package ru.dima.myblog.boot.service;

public interface TagManagerService {

//    List<Tag> findAllTagsToPost(long postId);

    void create(String post, long currentPostId);
}
