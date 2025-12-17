package com.macrochef.controller;

import com.macrochef.dto.CommentRequest;
import com.macrochef.dto.CommentResponse;
import com.macrochef.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes/{recipeId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // TEST için userId = 1
    @PostMapping
    public CommentResponse addComment(@PathVariable Long recipeId,
                                      @RequestBody CommentRequest req) {
        return commentService.addComment(recipeId, 1L, req);
    }

    @GetMapping
    public List<CommentResponse> getComments(@PathVariable Long recipeId) {
        return commentService.getCommentsByRecipe(recipeId);
    }
}
