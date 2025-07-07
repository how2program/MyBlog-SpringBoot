package ru.dima.myblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dima.myblog.dao.TagManagerDao;
import ru.dima.myblog.model.Tag;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagManagerServiceImpl implements TagManagerService {

    private final TagManagerDao tagManagerDao;

    @Autowired
    public TagManagerServiceImpl(TagManagerDao tagManagerDao) {
        this.tagManagerDao = tagManagerDao;
    }

    private List<Tag> parseTags(String tags, long currentPostId) {
        String[] splitTags = tags.split(",");
        List<Tag> resultTags = new ArrayList<>();
        for (String tag : splitTags) {
            Tag anotherTag = new Tag();
            anotherTag.setTag(tag);
            anotherTag.setPostId(currentPostId);
            resultTags.add(anotherTag);
        }
        return resultTags;
    }

//    @Override
//    public List<Tag> findAllTagsToPost(long postId) {
//        return tagManagerDao.findAllTagsToPost(postId);
//    }

    @Override
    public void create(String post, long currentPostId) {
        List<Tag> tags = parseTags(post, currentPostId);
        tagManagerDao.create(tags);
    }

}
