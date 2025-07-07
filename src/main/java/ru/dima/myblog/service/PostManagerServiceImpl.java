package ru.dima.myblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dima.myblog.dao.PostManagerDao;
import ru.dima.myblog.model.Post;
import java.util.List;
import java.util.Optional;

@Service
public class PostManagerServiceImpl implements PostManagerService {

    PostManagerDao postManagerDao;

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
    public void create(Post post) {
        postManagerDao.create(post);
    }

    @Override
    public void update(long id, Post updatedPost) {
        postManagerDao.update(id, updatedPost);
    }

    @Override
    public void deleteById(long id) {
        postManagerDao.deleteById(id);
    }
}
