package ru.dima.myblog.boot.dao;

import ru.dima.myblog.boot.model.Tag;

import java.util.List;

public interface TagManagerDao {

    List<Tag> findAllTagsToPost(long postId);

    void create(List<Tag> tags);

}
