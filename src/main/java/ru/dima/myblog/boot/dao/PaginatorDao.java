package ru.dima.myblog.boot.dao;

import ru.dima.myblog.boot.models.Post;

import java.util.List;

public interface PaginatorDao {

    public List<Post> findPostsPage(int offset, int limit);

    public long countPosts();

}
