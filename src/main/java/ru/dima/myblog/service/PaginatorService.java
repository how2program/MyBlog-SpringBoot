package ru.dima.myblog.service;

import ru.dima.myblog.model.Post;

import java.util.List;

public interface PaginatorService {

    public List<Post> findPostsPage(int offset, int limit);

    public long countPosts();

}
