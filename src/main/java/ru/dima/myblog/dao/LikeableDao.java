package ru.dima.myblog.dao;

import ru.dima.myblog.model.Post;

public interface LikeableDao {

    void like(Post post);

}
