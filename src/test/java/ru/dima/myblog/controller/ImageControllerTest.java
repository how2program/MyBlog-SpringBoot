package ru.dima.myblog.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.dima.myblog.model.Post;
import ru.dima.myblog.service.PostManagerService;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ImageControllerTest {

    private MockMvc mockMvc;
    private PostManagerService postManagerService;
    private ImageController imageController;

    @BeforeEach
    public void setup() {
        postManagerService = mock(PostManagerService.class);
        imageController = new ImageController(postManagerService);
        mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
    }

    @Test
    public void testGetImage() throws Exception {
        Long postId = 1L;
        byte[] imageData = {1, 2, 3, 4, 5};

        Post mockPost = mock(Post.class);
        when(postManagerService.findById(postId)).thenReturn(Optional.of(mockPost));
        when(mockPost.getImage()).thenReturn(imageData);

        mockMvc.perform(get("/posts/{id}/image", postId))
                .andExpect(status().isOk())
                .andExpect(content().bytes(imageData));

        verify(postManagerService).findById(postId);
    }
}
