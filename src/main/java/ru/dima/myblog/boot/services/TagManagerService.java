package ru.dima.myblog.boot.services;

public interface TagManagerService {

//    List<Tag> findAllTagsToPost(long postId);

    void create(String post, long currentPostId);
}
