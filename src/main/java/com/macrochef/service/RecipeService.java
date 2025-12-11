package com.macrochef.service;

import com.macrochef.dto.RecipeRequest;
import com.macrochef.dto.RecipeResponse;
import com.macrochef.entity.Recipe;
import com.macrochef.entity.User;
import com.macrochef.repository.RecipeRepository;
import com.macrochef.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    public RecipeResponse createRecipe(RecipeRequest request, Long userId) {

        User user = userRepository.findById(userId).orElseThrow();

        Recipe recipe = new Recipe();
        recipe.setTitle(request.getTitle());
        recipe.setDescription(request.getDescription());
        recipe.setInstructions(request.getInstructions());
        recipe.setImageUrl(request.getImageUrl());
        recipe.setUser(user);

        recipeRepository.save(recipe);

        return mapToResponse(recipe);
    }

    // --- DTO dönen yeni versiyon ---
    public List<RecipeResponse> getAllRecipes() {
        return recipeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Recipe entity lazım olacağı için içeride kullanıyoruz
    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id).orElseThrow();
    }

    // DTO dönen yeni detay endpoint
    public RecipeResponse getRecipeResponseById(Long id) {
        Recipe recipe = getRecipeById(id);
        return mapToResponse(recipe);
    }

    public void deleteRecipe(Long id, Long userId) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow();

        if (!recipe.getUser().getId().equals(userId)) {
            throw new RuntimeException("You cannot delete someone else's recipe!");
        }

        recipeRepository.delete(recipe);
    }

    // --- ENTITY → DTO mapper ---
    public RecipeResponse mapToResponse(Recipe recipe) {
        RecipeResponse res = new RecipeResponse();
        res.setId(recipe.getId());
        res.setTitle(recipe.getTitle());
        res.setDescription(recipe.getDescription());
        res.setInstructions(recipe.getInstructions());
        res.setImageUrl(recipe.getImageUrl());
        res.setCreatedAt(recipe.getCreatedAt().toString());
        res.setCreatedBy(recipe.getUser().getUsername());
        return res;
    }
}
