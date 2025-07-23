package ru.dima.myblog.boot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.dima.myblog.boot.models.Post;
import ru.dima.myblog.boot.services.PostManagerService;

import java.sql.SQLException;
import java.util.Optional;

@Controller
@RequestMapping
public class ImageController {

    PostManagerService postManagerService;

    @Autowired
    public ImageController(PostManagerService postManagerService) {
        this.postManagerService = postManagerService;
    }

    @GetMapping("/posts/{id}/image")
    @ResponseBody
    public byte[] getImage(@PathVariable(name = "id") Long id) throws SQLException {
        Optional<Post> post = postManagerService.findById(id);
        return post.get().getImage();
    }
}
