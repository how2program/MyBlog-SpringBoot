package ru.dima.myblog.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ru.dima.myblog.model.Commentary;
import ru.dima.myblog.model.Post;
import ru.dima.myblog.service.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PostControllerTest {

    private MockMvc mockMvc;

    private PostManagerService postManagerService = Mockito.mock(PostManagerService.class);
    private Likeable likeHandler = Mockito.mock(Likeable.class);
    private TagManagerService tagManagerService = Mockito.mock(TagManagerService.class);
    private CommentaryManagerService commentaryManagerService = Mockito.mock(CommentaryManagerService.class);
    private PaginatorService paginatorService = Mockito.mock(PaginatorService.class);

    private PostController postController;

    @BeforeEach
    public void setup() {
        postController = new PostController(
                postManagerService,
                likeHandler,
                tagManagerService,
                paginatorService
        );
                mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
    }

    @Test
    public void testShowAll() throws Exception {

        Post post = new Post();
        post.setId(1);
        post.setHeading("heading");
        List<Post> posts = List.of(post);

        Mockito.when(paginatorService.findPostsPage(0, 10)).thenReturn(posts);
        Mockito.when(paginatorService.countPosts()).thenReturn(1L);

        mockMvc.perform(get("/posts?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(view().name("allposts"))
                .andExpect(model().attribute("posts", posts))
                .andExpect(model().attribute("currentPage", 0))
                .andExpect(model().attribute("totalPages", 1));
    }

    @Test
    public void testShowOnePost() throws Exception {
        long postId = 1;

        Commentary commentary = new Commentary();
        commentary.setText("commentary");

        Post post = new Post();
        post.setId(postId);
        post.setHeading("heading");
        post.setCommentaries(List.of(commentary));

        Mockito.when(postManagerService.findById(postId)).thenReturn(Optional.of(post));

        mockMvc.perform(get("/posts/" + postId))
                .andExpect(status().isOk())
                .andExpect(view().name("singlepost"))
                .andExpect(model().attribute("post", Optional.of(post)))
                .andExpect(model().attribute("commentaries", post.getCommentaries()));
    }

    @Test
    public void testPostObjectInitializer() throws Exception {
        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(view().name("allposts"))
                .andExpect(model().attributeExists("postToCreate", "commentary"))
                .andExpect(model().attribute("postToCreate", new Post()))
                .andExpect(model().attribute("commentary", new Commentary()));
    }

    @Test
    public void testCreatePost() throws Exception {
        Post savedPost = new Post();
        savedPost.setId(1);
        savedPost.setHeading("new post");

        Mockito.when(postManagerService.create(Mockito.any(Post.class))).thenReturn(savedPost.getId());

        MockMultipartFile mockFile = new MockMultipartFile(
                "file",
                "filename.txt",
                "text/plain",
                "содержимое файла".getBytes()
        );

        mockMvc.perform(
                        multipart("/posts")
                                .file("myImage", "тестовый файл".getBytes())
                                .param("postToCreate.heading", "Заголовок")
                                .param("postToCreate.content", "Текст поста")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts/1"));

        Mockito.verify(postManagerService).create(Mockito.any(Post.class));
    }

    @Test
    public void testLike() throws Exception {
        long postId = 1;

        doNothing().when(likeHandler).like(postId);

        mockMvc.perform(post("/posts/like/{postId}", postId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts/" + postId));
    }

    @Test
    public void testDeletePost() throws Exception {
        long postId = 123;

        mockMvc.perform(post("/posts/{id}", postId)
                        .param("_method", "delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts"));

        Mockito.verify(postManagerService).deleteById(postId);
    }

    @Test
    public void testUpdatePost() throws Exception {
        long postId = 1L;


        Post updatedPost = new Post();
        updatedPost.setId(1);
        updatedPost.setHeading("heading");

        MockMultipartFile imageFile = new MockMultipartFile(
                "myImage",
                "test.jpg",
                "image/jpeg",
                "fake image content".getBytes()
        );

        MockMultipartHttpServletRequestBuilder builder =
                (MockMultipartHttpServletRequestBuilder) multipart("/posts/{id}", postId)
                .file(imageFile)
                .param("_method", "patch")
                .param("title", "Updated Title");

        mockMvc.perform(builder)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts/" + postId));

         Mockito.verify(postManagerService).update(eq(postId), any(Post.class));
    }

    @Test
    public void testFilterPosts() throws Exception {
        String tag = "testTag";

        Post post1 = new Post();
        post1.setId(1);
        post1.setHeading("heading");
        Post post2 = new Post();
        post2.setId(2);
        post2.setHeading("heading");

        List<Post> mockPosts = Arrays.asList(
                post1,
                post2
        );
        when(postManagerService.findByTag(tag)).thenReturn(mockPosts);

        mockMvc.perform(get("/posts/filter")
                        .param("myTag", tag))
                .andExpect(status().isOk())
                .andExpect(view().name("allposts"))
                .andExpect(model().attribute("posts", mockPosts));

        verify(postManagerService).findByTag(tag);
    }


}