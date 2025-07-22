package ru.dima.myblog.boot.service;

import ru.dima.myblog.boot.model.Post;

import java.util.List;

public interface PaginatorService {

    public List<Post> findPostsPage(int offset, int limit);

    public long countPosts();

}
