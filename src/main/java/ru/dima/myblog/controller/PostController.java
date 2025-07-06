package ru.dima.myblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dima.myblog.model.Post;
import ru.dima.myblog.service.Likeable;
import ru.dima.myblog.service.PostManagerService;
import ru.dima.myblog.service.PostManagerServiceImpl;

@Controller
@RequestMapping("/posts")
public class PostController {

    PostManagerService postManagerService;
    Likeable likeHandler;

    @Autowired
    public PostController(PostManagerService postManagerService, Likeable likeHandler) {
        this.postManagerService = postManagerService;
        this.likeHandler = likeHandler;
    }

    @GetMapping
    public String showAllPosts(Model model) {
        model.addAttribute("posts", postManagerService.findAll());
        return "allposts";
    }

    @GetMapping("/{id}")
    public String showOnePost(Model model, @PathVariable(name = "id") long id) {
        model.addAttribute("post", postManagerService.findById(id));
        return "singlepost";
    }

    @ModelAttribute(name = "postToCreate")
    public Post postObjectInitializer() {
        return new Post();
    }

    @PostMapping
    public String createPost(@ModelAttribute(name = "postToCreate") Post post) {
        postManagerService.create(post);
        return "redirect:/posts";
    }

    @PostMapping("/like/{postId}")
    public String like(@PathVariable long postId, Model model) {
        likeHandler.like(postId);
        model.addAttribute("post", postManagerService.findById(postId));
        return "redirect:/posts/" + postId;
    }


}
