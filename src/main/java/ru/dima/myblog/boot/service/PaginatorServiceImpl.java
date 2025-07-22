package ru.dima.myblog.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dima.myblog.boot.dao.PaginatorDao;
import ru.dima.myblog.boot.model.Post;

import java.util.List;

@Service
public class PaginatorServiceImpl implements PaginatorService {

    PaginatorDao paginatorDao;

    @Autowired
    public PaginatorServiceImpl(PaginatorDao paginatorDao) {
        this.paginatorDao = paginatorDao;
    }

    @Override
    public List<Post> findPostsPage(int offset, int limit) {
        return paginatorDao.findPostsPage(offset, limit);
    }

    @Override
    public long countPosts() {
        return paginatorDao.countPosts();
    }
}
