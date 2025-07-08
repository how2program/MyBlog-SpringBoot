package ru.dima.myblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.dima.myblog.model.Commentary;
import ru.dima.myblog.model.Post;
import ru.dima.myblog.service.CommentaryManagerService;
import ru.dima.myblog.service.Likeable;
import ru.dima.myblog.service.PostManagerService;
import ru.dima.myblog.service.TagManagerService;

import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping("/posts")
public class PostController {

    private static long currentPostId = 4;

    private final PostManagerService postManagerService;
    private final TagManagerService tagManagerService;
    private final CommentaryManagerService commentaryManagerService;
    private final Likeable likeHandler;

    @Autowired
    public PostController(PostManagerService postManagerService,
                          Likeable likeHandler,
                          TagManagerService tagManagerService,
                          CommentaryManagerService commentaryManagerService) {
        this.postManagerService = postManagerService;
        this.likeHandler = likeHandler;
        this.tagManagerService = tagManagerService;
        this.commentaryManagerService = commentaryManagerService;
    }

    @GetMapping
    public String showAllPosts(Model model) {
        model.addAttribute("posts", postManagerService.findAll());
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
    public String createPost(@ModelAttribute(name = "postToCreate") Post post
                             /*@RequestParam(name = "image") MultipartFile imageFile */) throws IOException, SQLException {

        postManagerService.create(post);
        tagManagerService.create(post.getTagsInString(), currentPostId);
        currentPostId++;
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

    @PostMapping(value = "/{postId}/commentary")
    public String createCommentary(@PathVariable long postId,
                                   @ModelAttribute(name = "commentary") Commentary commentary,
                                   Model model) {
        commentaryManagerService.createCommentary(postId, commentary);
        return "redirect:/posts/" + postId;
    }

    @GetMapping("/{postId}/commentary/{commentaryId}/edit")
    public String editCommentaryForm(@PathVariable(name = "postId") long postId,
                                     @PathVariable(name = "commentaryId") long commentaryId,
                                     Model model) {
        model.addAttribute("commentToUpdate",
                commentaryManagerService.findCommentaryByPostAndCommentaryId(postId, commentaryId).get());
        return "changeCommentaryForm";
    }

    @PostMapping(value ="/{postId}/commentary/{commentaryId}/new", params = "_method=patch")
    public String updateCommentary(@PathVariable(name = "postId") long postId,
                                 @PathVariable(name = "commentaryId") long commentaryId,
                                   @ModelAttribute("commentToUpdate") Commentary updatedCommentary) {
        commentaryManagerService.updateCommentary(postId, commentaryId, updatedCommentary);
        return "redirect:/posts/" + postId;
    }

    @PostMapping(value ="/{postId}/commentary/{commentaryId}", params = "_method=delete")
    public String deleteCommentary(@PathVariable(name = "postId") long postId,
                                   @PathVariable(name = "commentaryId") long commentaryId) {
        commentaryManagerService.deleteCommentary(postId, commentaryId);
        return "redirect:/posts/" + postId;
    }

}
