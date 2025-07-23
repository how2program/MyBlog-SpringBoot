package ru.dima.myblog.boot.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.test.context.SpringBootTest;
import ru.dima.myblog.boot.dao.PostManagerDao;
import ru.dima.myblog.boot.models.Post;
import ru.dima.myblog.boot.models.Tag;

@SpringBootTest
public class PostManagerServiceUT {
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

    @Test
    public void testDeleteById() {
        long id = 123;

        postManagerServiceImpl.deleteById(id);

        verify(postManagerDao, times(1)).deleteById(id);
    }

    @Test
    public void testFindByTag() {
        String tag = "java";
        Tag tag1 = new Tag();
        tag1.setTag("java");
        Tag tag2 = new Tag();
        tag2.setTag("it");
        Tag tag3 = new Tag();
        tag3.setTag("games");

        Post post1 = new Post();
        post1.setHeading("Java Post 1");
        post1.setTags(List.of(tag1, tag2));

        Post post2 = new Post();
        post2.setHeading("Java Post 2");
        post2.setTags(List.of(tag2, tag3));

        List<Post> expectedPosts = List.of(post1, post2);

        when(postManagerDao.findByTag(tag, 0, 10)).thenReturn(expectedPosts);

        List<Post> result = postManagerServiceImpl.findByTag(tag, 0, 10);

        assertThat(result).containsExactlyInAnyOrder(post1, post2);
        verify(postManagerDao).findByTag(tag, 0, 10);
    }
}
