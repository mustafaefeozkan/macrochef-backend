package com.macrochef.controller;

import com.macrochef.dto.CommentRequest;
import com.macrochef.dto.CommentResponse;
import com.macrochef.entity.Comment;
import com.macrochef.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes/{recipeId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // -------------------------
    // CREATE COMMENT (JWT)
    // -------------------------
    @PostMapping
    public CommentResponse addComment(
            @PathVariable Long recipeId,
            @RequestBody CommentRequest req
    ) {
        Comment comment = commentService.addComment(recipeId, req);
        return mapToResponse(comment);
    }

    // -------------------------
    // READ COMMENTS BY RECIPE
    // -------------------------
    @GetMapping
    public List<CommentResponse> getComments(@PathVariable Long recipeId) {
        return commentService.getCommentsByRecipe(recipeId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // -------------------------
    // DELETE COMMENT (JWT)
    // -------------------------
    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }

    // -------------------------
    // ENTITY → DTO
    // -------------------------
    private CommentResponse mapToResponse(Comment comment) {

        CommentResponse res = new CommentResponse();
        res.setId(comment.getId());
        res.setText(comment.getText());
        res.setRating(comment.getRating());
        res.setCreatedAt(comment.getCreatedAt().toString());
        res.setUsername(comment.getUser().getUsername());

        return res;
    }
}
