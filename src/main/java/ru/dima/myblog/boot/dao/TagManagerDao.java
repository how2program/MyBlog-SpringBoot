package ru.dima.myblog.boot.dao;

import ru.dima.myblog.boot.models.Tag;

import java.util.List;

public interface TagManagerDao {

    List<Tag> findAllTagsToPost(long postId);

    void create(List<Tag> tags);

}
