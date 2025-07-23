package ru.dima.myblog.boot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dima.myblog.boot.models.Commentary;
import ru.dima.myblog.boot.services.CommentaryManagerService;

@Controller
@RequestMapping("/posts")
public class CommentaryController {

    CommentaryManagerService commentaryManagerService;

    @Autowired
    public CommentaryController(CommentaryManagerService commentaryManagerService) {
        this.commentaryManagerService = commentaryManagerService;
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
