package ru.dima.myblog.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.dima.myblog.config.DataSourceConfiguration;
import ru.dima.myblog.config.ServletConfiguration;
import ru.dima.myblog.config.ThymeleafConfiguration;
import ru.dima.myblog.dao.PostManagerDao;
import ru.dima.myblog.model.Commentary;
import ru.dima.myblog.model.Post;
import ru.dima.myblog.model.Tag;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ServletConfiguration.class, DataSourceConfiguration.class, ThymeleafConfiguration.class})
@WebAppConfiguration
public class PostManagerServiceTestIntegration {

    @Autowired
    private PostManagerService postManagerService;

    @Autowired
    private PostManagerDao postManagerDao;

    @Test
    public void testFindAllIntegration() {
        Post post = new Post();
        post.setHeading("heading");
        post.setBody("body");
        post.setLocalDateTime(LocalDateTime.now());
        post.setImage("image".getBytes());
        post.setCommentaries(List.of(new Commentary(), new Commentary()));
        post.setTags(List.of(new Tag(), new Tag()));
        post.setLikes(0);
        postManagerDao.create(post);

        List<Post> posts = postManagerService.findAll();

        assertNotNull(posts);
        assertTrue(!posts.isEmpty());
        assertTrue(posts.stream().anyMatch(p -> "body".equals(p.getBody())));
    }

    @Test
    public void testFindByIdIntegration() {
        Post post = new Post();
        post.setHeading("heading");
        post.setBody("body");
        post.setLocalDateTime(LocalDateTime.now());
        post.setImage("image".getBytes());
        post.setCommentaries(List.of(new Commentary(), new Commentary()));
        post.setTags(List.of(new Tag(), new Tag()));
        post.setLikes(0);
        postManagerDao.create(post);

        long thereAre30ManuallyCreatedPosts = 31;

        Optional<Post> foundPost = postManagerService.findById(thereAre30ManuallyCreatedPosts);

        assertTrue(foundPost.isPresent());
        assertEquals("heading", foundPost.get().getHeading());
    }

    @Test
    public void testCreateIntegration() {

        Post post = new Post();
        post.setHeading("heading");

        long id = postManagerService.create(post);

        assertTrue(id > 0);

        Optional<Post> savedPost = postManagerDao.findById(id);
        assertNotNull(savedPost.get());
        assertEquals("heading", savedPost.get().getHeading());

        assertNotNull(savedPost.get().getLocalDateTime());
        assertTrue(savedPost.get().getLocalDateTime().isBefore(LocalDateTime.now().plusSeconds(1)));
    }

    @Test
    public void testUpdateIntregration() {

        Post originalPost = new Post();
        originalPost.setHeading("heading");

        long id = postManagerService.create(originalPost);

        Post updatedPost = new Post();
        updatedPost.setHeading("newHeading");

        postManagerService.update(id, updatedPost);

        Optional<Post> retrievedPost = postManagerDao.findById(id);
        assertNotNull(retrievedPost);
        assertEquals("newHeading", retrievedPost.get().getHeading());
    }
}