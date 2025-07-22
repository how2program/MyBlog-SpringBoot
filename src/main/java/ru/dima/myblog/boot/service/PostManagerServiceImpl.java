package ru.dima.myblog.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dima.myblog.boot.dao.PostManagerDao;
import ru.dima.myblog.boot.model.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostManagerServiceImpl implements PostManagerService {

    private final PostManagerDao postManagerDao;

    @Autowired
    public PostManagerServiceImpl(PostManagerDao postManagerDao) {
        this.postManagerDao = postManagerDao;
    }


    @Override
    public List<Post> findAll() {
        return postManagerDao.findAll();
    }

    @Override
    public Optional<Post> findById(long id) {
        return postManagerDao.findById(id);
    }

    @Override
    public long create(Post post) {
        post.setLocalDateTime(LocalDateTime.now());
        return postManagerDao.create(post);
    }

    @Override
    public void update(long id, Post updatedPost) {
        postManagerDao.update(id, updatedPost);
    }

    @Override
    public void deleteById(long id) {
        postManagerDao.deleteById(id);
    }

    @Override
    public List<Post> findByTag(String tag) {
        return postManagerDao.findByTag(tag);
    }
}