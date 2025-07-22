package ru.dima.myblog.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.dima.myblog.boot.model.Commentary;
import ru.dima.myblog.boot.model.Post;
import ru.dima.myblog.boot.service.Likeable;
import ru.dima.myblog.boot.service.PaginatorService;
import ru.dima.myblog.boot.service.PostManagerService;
import ru.dima.myblog.boot.service.TagManagerService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private static final int PAGE_SIZE = 10;

    private final PostManagerService postManagerService;
    private final TagManagerService tagManagerService;
    private final Likeable likeHandler;
    private final PaginatorService paginatorService;

    @Autowired
    public PostController(PostManagerService postManagerService,
                          Likeable likeHandler,
                          TagManagerService tagManagerService,
                          PaginatorService paginatorService) {
        this.postManagerService = postManagerService;
        this.likeHandler = likeHandler;
        this.tagManagerService = tagManagerService;
        this.paginatorService = paginatorService;
    }

    @GetMapping
    public String showAll(@RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "10") int size,
                          Model model) {
        int offset = page * size;
        List<Post> posts = paginatorService.findPostsPage(offset, size);
        long totalPosts = paginatorService.countPosts();

        int totalPages = (int) Math.ceil((double) totalPosts / size);

        model.addAttribute("posts", posts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "allposts";
    }

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
            return "redirect:/posts/" + currentPostId;
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
                             @ModelAttribute("post") Post updatedPost,
                             @RequestParam("myImage") MultipartFile myImage) throws IOException {
        byte[] imageBytes = myImage.getBytes();
        updatedPost.setImage(imageBytes);
        postManagerService.update(id, updatedPost);
        return "redirect:/posts/" + id;
    }

    @GetMapping("/filter")
    public String filterPosts(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(name = "myTag") String myTag,
                              Model model) {
        int offset = page * size;
        List<Post> filteredPosts = postManagerService.findByTag(myTag, offset, size);
        long totalPosts = paginatorService.countPosts();
        int totalPages = (int) Math.ceil((double) totalPosts / size);

        model.addAttribute("posts", filteredPosts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "allposts";
    }

}
