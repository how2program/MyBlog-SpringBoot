package ru.dima.myblog.tests.servicePostTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.dima.myblog.config.ServletConfiguration;
import ru.dima.myblog.model.Commentary;
import ru.dima.myblog.model.Post;
import ru.dima.myblog.model.Tag;
import ru.dima.myblog.service.CommentaryManagerService;
import ru.dima.myblog.service.PostManagerService;
import ru.dima.myblog.service.TagManagerService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = {ServletConfiguration.class})
@WebAppConfiguration
public class ServiceLayerTest {

    private final PostManagerService postManagerService;
    private final TagManagerService tagManagerService;
    private final CommentaryManagerService commentaryManagerService;

    private Post post;
    private Tag tag;
    private Commentary commentary;

    @Autowired
    public ServiceLayerTest(PostManagerService postManagerService,
                            TagManagerService tagManagerService,
                            CommentaryManagerService commentaryManagerService) {
        this.postManagerService = postManagerService;
        this.tagManagerService = tagManagerService;
        this.commentaryManagerService = commentaryManagerService;
    }

    @BeforeEach
    public void beforeEachObjectSetup() {
        post = new Post();
        tag = new Tag();
        commentary = new Commentary();
    }

    @Test
    @DisplayName("Тестирование работы PostManagerService на объекте модели Post")
    public void postServiceTestHeading() {
        String testHeading = "testHeading";
        post.setHeading(testHeading);
        postManagerService.create(post);
        Optional<Post> result = postManagerService.findById(4);
        assertTrue(result.isPresent());
        assertEquals(testHeading, result.get().getHeading());
    }

    @Test
    @DisplayName("Тестирование правильного сохранения id на объекте Модели Post")
    public void postServiceTestId() {
        String testHeading = "testHeading";
        post.setHeading(testHeading);
        postManagerService.create(post);
        Optional<Post> result = postManagerService.findById(4);
        assertTrue(result.isPresent());
        assertEquals(4, result.get().getId());
        //Четвёртый номер, потому-что в тестовых скриптах уже есть три ручных ввода.
    }
}
