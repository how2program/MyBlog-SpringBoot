package ru.dima.myblog.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.extension.ExtendWith;
import ru.dima.myblog.dao.PostManagerDao;
import ru.dima.myblog.model.Post;

@ExtendWith(MockitoExtension.class)
public class PostManagerServiceTestUnit {

    @Mock
    private PostManagerDao postManagerDao;

    @InjectMocks
    private PostManagerServiceImpl postManagerServiceImpl;

    @Test
    public void testFindAll() {
        List<Post> mockPosts = Arrays.asList(new Post(), new Post());
        when(postManagerDao.findAll()).thenReturn(mockPosts);

        List<Post> result = postManagerServiceImpl.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(postManagerDao).findAll();
    }


    @Test
    public void testFindById() {
        long postId = 2;

        when(postManagerDao.findById(postId)).thenReturn(Optional.empty());

        Optional<Post> result = postManagerServiceImpl.findById(postId);

        assertFalse(result.isPresent());
        verify(postManagerDao).findById(postId);
    }

    @Test
    public void testCreate() {
        Post post = new Post();
        post.setHeading("heading");

        when(postManagerDao.create(any(Post.class))).thenReturn(123L);

        long generatedId = postManagerServiceImpl.create(post);

        assertNotNull(post.getLocalDateTime());
        assertTrue(post.getLocalDateTime().isBefore(LocalDateTime.now().plusSeconds(1)));

        assertEquals(123, generatedId);

        verify(postManagerDao).create(post);
    }

    @Test
    public void testUpdateIntegration() {
        long id = 1;
        Post updatedPost = new Post();
        updatedPost.setHeading("newHeading");

       postManagerServiceImpl.update(id, updatedPost);

        verify(postManagerDao, times(1)).update(id, updatedPost);
    }
}