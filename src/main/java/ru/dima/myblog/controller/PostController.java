package ru.dima.myblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import ru.dima.myblog.dao.PaginatorDao;
import ru.dima.myblog.model.Commentary;
import ru.dima.myblog.model.Post;
import ru.dima.myblog.service.CommentaryManagerService;
import ru.dima.myblog.service.Likeable;
import ru.dima.myblog.service.PostManagerService;
import ru.dima.myblog.service.TagManagerService;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class PostController {

    private static final int PAGE_SIZE = 10;

    private final PostManagerService postManagerService;
    private final TagManagerService tagManagerService;
    private final CommentaryManagerService commentaryManagerService;
    private final Likeable likeHandler;
    private final PaginatorDao paginatorDao;

    @Autowired
    public PostController(PostManagerService postManagerService,
                          Likeable likeHandler,
                          TagManagerService tagManagerService,
                          CommentaryManagerService commentaryManagerService, PaginatorDao paginatorDao) {
        this.postManagerService = postManagerService;
        this.likeHandler = likeHandler;
        this.tagManagerService = tagManagerService;
        this.commentaryManagerService = commentaryManagerService;
        this.paginatorDao = paginatorDao;
    }

    @GetMapping
    public String showAllPosts(Model model) {
        model.addAttribute("posts", postManagerService.findAll());
        return "allposts";
    }

//    @GetMapping В ПРОЦЕССЕ
//    public String showAllPosts(@RequestParam(value = "page", defaultValue = "1") int page,
//                               Model model) {
//        int offset = (page - 1) * PAGE_SIZE;
//        int totalPosts = postManagerService.findAll().size();
//        int totalPages = (int) Math.ceil((double) totalPosts / PAGE_SIZE);
//
//        model.addAttribute("posts", paginatorDao.getAllPostsByPage(offset, PAGE_SIZE))
//    }

    @GetMapping("/{id}")
    public String showOnePost(Model model, @PathVariable(name = "id") long id) {
        model.addAttribute("post", postManagerService.findById(id));
        model.addAttribute("commentaries", postManagerService.findById(id).get().getCommentaries());
        return "singlepost";
    }

    @ModelAttribute(name = "postToCreate")
    public Post postObjectInitializer() {
        return new Post();
    }

    @ModelAttribute(name = "commentary")
    public Commentary commentaryObjectInitializer() {
        return new Commentary();
    }

    @PostMapping
    public String createPost(@ModelAttribute(name = "postToCreate") Post post,
                             @RequestParam(name = "myImage") MultipartFile myImage) throws IOException {
            byte[] imageBytes = myImage.getBytes();
            post.setImage(imageBytes);
            long currentPostId = postManagerService.create(post);
            tagManagerService.create(post.getTagsInString(), currentPostId);
            return "redirect:/posts";
    }

    @PostMapping("/like/{postId}")
    public String like(@PathVariable long postId, Model model) {
        likeHandler.like(postId);
        return "redirect:/posts/" + postId;
    }

    @PostMapping(value = "/{id}", params = "_method=delete")
    public String deletePost(@PathVariable(name = "id") long id) {
        postManagerService.deleteById(id);
        return "redirect:/posts";
    }

    @PostMapping(value = "/{id}", params = "_method=patch")
    public String updatePost(@PathVariable(name = "id") long id,
                             @ModelAttribute("post") Post updatedPost) {
        postManagerService.update(id, updatedPost);
        return "redirect:/posts/" + id;
    }

    @GetMapping("/filter")
    public String filterPosts(@RequestParam(name = "myTag") String myTag,
                              Model model) {
        List<Post> filteredPosts = postManagerService.findByTag(myTag);
        model.addAttribute("posts", filteredPosts);
        return "allposts";
    }

}
