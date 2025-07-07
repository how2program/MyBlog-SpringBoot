package ru.dima.myblog.dao;

import ru.dima.myblog.model.Tag;

import java.util.List;
import java.util.Optional;

public interface TagManagerDao {

    List<Tag> findAllTagsToPost(long postId);

    void create(List<Tag> tags);

}
