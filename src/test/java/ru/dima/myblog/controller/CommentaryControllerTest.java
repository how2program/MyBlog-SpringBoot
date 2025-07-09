package ru.dima.myblog.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.dima.myblog.model.Commentary;
import ru.dima.myblog.service.CommentaryManagerService;

import java.util.Optional;

public class CommentaryControllerTest {

    private MockMvc mockMvc;
    private CommentaryManagerService commentaryManagerService;
    private CommentaryController commentaryController;

    @BeforeEach
    public void setup() {
        commentaryManagerService = mock(CommentaryManagerService.class);
        commentaryController = new CommentaryController(commentaryManagerService);
        mockMvc = MockMvcBuilders.standaloneSetup(commentaryController).build();
    }

    @Test
    public void testCreateCommentary() throws Exception {
        long postId = 42L;

        String commentaryText = "Это тестовый комментарий";

        mockMvc.perform(post("/posts/" + postId + "/commentary")
                                .param("commentary.text", commentaryText)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts/" + postId));

        verify(commentaryManagerService).createCommentary(eq(postId), any(Commentary.class));
    }

    @Test
    public void testEditCommentaryForm() throws Exception {
        long postId = 10;
        long commentaryId = 20;

        Commentary commentary = new Commentary();
        commentary.setId(commentaryId);

        when(commentaryManagerService.findCommentaryByPostAndCommentaryId(postId, commentaryId))
                .thenReturn(Optional.of(commentary));

        mockMvc.perform(get("/posts/" + postId + "/commentary/" + commentaryId + "/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("changeCommentaryForm"))
                .andExpect(model().attributeExists("commentToUpdate"))
                .andExpect(model().attribute("commentToUpdate", commentary));

        verify(commentaryManagerService).findCommentaryByPostAndCommentaryId(postId, commentaryId);
    }

    @Test
    public void testUpdateCommentary() throws Exception {
        long postId = 15;
        long commentaryId = 25;

        Commentary updatedCommentary = new Commentary();
        updatedCommentary.setId(commentaryId);
        updatedCommentary.setText("Обновленный комментарий");

        mockMvc.perform(post("/posts/" + postId + "/commentary/" + commentaryId + "/new")
                                .param("_method", "patch")
                                .param("commentToUpdate.text", "Обновленный комментарий")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts/" + postId));

        verify(commentaryManagerService).updateCommentary(eq(postId), eq(commentaryId), any(Commentary.class));
    }

    @Test
    public void testDeleteCommentary() throws Exception {
        long postId = 15;
        long commentaryId = 25;

        mockMvc.perform(post("/posts/" + postId + "/commentary/" + commentaryId)
                        .param("_method", "delete")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts/" + postId));

        verify(commentaryManagerService).deleteCommentary(postId, commentaryId);
    }

}