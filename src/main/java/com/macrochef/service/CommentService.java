package com.macrochef.service;

import com.macrochef.dto.CommentRequest;
import com.macrochef.entity.Comment;
import com.macrochef.entity.Recipe;
import com.macrochef.entity.User;
import com.macrochef.repository.CommentRepository;
import com.macrochef.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final RecipeRepository recipeRepository;
    private final UserService userService;

    // -------------------------
    // CREATE COMMENT (JWT)
    // -------------------------
    public Comment addComment(Long recipeId, CommentRequest request) {

        User currentUser = userService.getCurrentUser();

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        Comment comment = new Comment();
        comment.setRecipe(recipe);
        comment.setUser(currentUser);
        comment.setText(request.getText());
        comment.setRating(request.getRating());
        comment.setCreatedAt(LocalDateTime.now());

        return commentRepository.save(comment);
    }

    // -------------------------
    // READ COMMENTS BY RECIPE
    // -------------------------
    public List<Comment> getCommentsByRecipe(Long recipeId) {

        // recipe var mı yok mu kontrolü (temiz API davranışı)
        recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        return commentRepository.findByRecipeId(recipeId);
    }

    // -------------------------
    // DELETE COMMENT (JWT + OWNERSHIP)
    // -------------------------
    @Transactional
    public void deleteComment(Long commentId) {

        User currentUser = userService.getCurrentUser();

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("You cannot delete someone else's comment");
        }

        commentRepository.delete(comment);
    }
}
