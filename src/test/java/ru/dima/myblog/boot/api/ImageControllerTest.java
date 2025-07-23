package ru.dima.myblog.boot.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.dima.myblog.boot.models.Post;
import ru.dima.myblog.boot.services.PostManagerService;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ImageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostManagerService postManagerService;

    @InjectMocks
    private ImageController imageController;

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
