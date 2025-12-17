package com.macrochef.service;

import com.macrochef.dto.CommentRequest;
import com.macrochef.dto.CommentResponse;
import com.macrochef.entity.Comment;
import com.macrochef.entity.Recipe;
import com.macrochef.entity.User;
import com.macrochef.repository.CommentRepository;
import com.macrochef.repository.RecipeRepository;
import com.macrochef.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    public CommentResponse addComment(Long recipeId, Long userId, CommentRequest req) {

        if (req.getRating() < 1 || req.getRating() > 5) {
            throw new RuntimeException("Rating must be between 1 and 5");
        }

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = new Comment();
        comment.setRecipe(recipe);
        comment.setUser(user);
        comment.setText(req.getText());
        comment.setRating(req.getRating());

        commentRepository.save(comment);

        return mapToResponse(comment);
    }

    public List<CommentResponse> getCommentsByRecipe(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        return commentRepository.findByRecipe(recipe)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private CommentResponse mapToResponse(Comment comment) {
        CommentResponse res = new CommentResponse();
        res.setId(comment.getId());
        res.setUsername(comment.getUser().getUsername());
        res.setText(comment.getText());
        res.setRating(comment.getRating());
        res.setCreatedAt(comment.getCreatedAt().toString());
        return res;
    }
}
