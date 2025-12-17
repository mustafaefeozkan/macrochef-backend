package com.macrochef.repository;

import com.macrochef.entity.Comment;
import com.macrochef.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByRecipe(Recipe recipe);
}
