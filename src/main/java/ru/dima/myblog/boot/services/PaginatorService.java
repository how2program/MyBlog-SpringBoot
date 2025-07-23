package ru.dima.myblog.boot.services;

import ru.dima.myblog.boot.models.Post;

import java.util.List;

public interface PaginatorService {

    public List<Post> findPostsPage(int offset, int limit);

    public long countPosts();

}
