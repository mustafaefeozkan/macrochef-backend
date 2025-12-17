package com.macrochef.service;

import com.macrochef.dto.RecipeIngredientResponse;
import com.macrochef.dto.RecipeRequest;
import com.macrochef.dto.RecipeResponse;
import com.macrochef.entity.Recipe;
import com.macrochef.entity.RecipeIngredient;
import com.macrochef.entity.User;
import com.macrochef.repository.RecipeRepository;
import com.macrochef.repository.UserRepository;
import com.macrochef.entity.Category;
import com.macrochef.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    // -------------------------
    // CREATE
    // -------------------------
    public RecipeResponse createRecipe(RecipeRequest request, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Recipe recipe = new Recipe();
        recipe.setTitle(request.getTitle());
        recipe.setDescription(request.getDescription());
        recipe.setInstructions(request.getInstructions());
        recipe.setImageUrl(request.getImageUrl());
        recipe.setUser(user);

        if (request.getCategoryId() != null) {
            Category category = categoryRepository
                    .findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            recipe.setCategory(category);
        }


        recipeRepository.save(recipe);

        return mapToResponse(recipe);
    }

    // -------------------------
    // READ ALL
    // -------------------------
    public List<RecipeResponse> getAllRecipes() {
        return recipeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // -------------------------
    // READ BY ID (ENTITY)
    // -------------------------
    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));
    }

    // -------------------------
    // READ BY ID (DTO)
    // -------------------------
    public RecipeResponse getRecipeResponseById(Long id) {
        return mapToResponse(getRecipeById(id));
    }

    // -------------------------
    // DELETE
    // -------------------------
    public void deleteRecipe(Long id, Long userId) {

        Recipe recipe = getRecipeById(id);

        if (!recipe.getUser().getId().equals(userId)) {
            throw new RuntimeException("You cannot delete someone else's recipe");
        }

        recipeRepository.delete(recipe);
    }

    // -------------------------
    // ENTITY → DTO + NUTRITION
    // -------------------------
    public RecipeResponse mapToResponse(Recipe recipe) {

        double totalCalories = 0;
        double totalProtein = 0;
        double totalCarbs = 0;
        double totalFat = 0;

        List<RecipeIngredientResponse> ingredientResponses = new ArrayList<>();

        if (recipe.getIngredients() != null) {
            for (RecipeIngredient ri : recipe.getIngredients()) {

                double factor = ri.getAmountInGrams() / 100.0;

                double calories = ri.getIngredient().getCalories100g() * factor;
                double protein  = ri.getIngredient().getProtein100g()  * factor;
                double carbs    = ri.getIngredient().getCarbs100g()    * factor;
                double fat      = ri.getIngredient().getFat100g()      * factor;

                totalCalories += calories;
                totalProtein  += protein;
                totalCarbs    += carbs;
                totalFat      += fat;

                RecipeIngredientResponse dto = new RecipeIngredientResponse();
                dto.setIngredientId(ri.getIngredient().getId());
                dto.setIngredientName(ri.getIngredient().getName());
                dto.setAmountInGrams(ri.getAmountInGrams());
                dto.setCalories(calories);
                dto.setProtein(protein);
                dto.setCarbs(carbs);
                dto.setFat(fat);

                ingredientResponses.add(dto);
            }
        }

        RecipeResponse res = new RecipeResponse();
        res.setId(recipe.getId());
        res.setTitle(recipe.getTitle());
        res.setDescription(recipe.getDescription());
        res.setInstructions(recipe.getInstructions());
        res.setImageUrl(recipe.getImageUrl());
        res.setCreatedAt(recipe.getCreatedAt().toString());
        res.setCreatedBy(recipe.getUser().getUsername());

        res.setTotalCalories(totalCalories);
        res.setTotalProtein(totalProtein);
        res.setTotalCarbs(totalCarbs);
        res.setTotalFat(totalFat);


        res.setIngredients(ingredientResponses);

        if (recipe.getCategory() != null) {
            res.setCategoryName(recipe.getCategory().getName());
        }


        return res;
    }
}
