package com.macrochef.controller;

import com.macrochef.dto.RecipeRequest;
import com.macrochef.dto.RecipeResponse;
import com.macrochef.service.RecipeService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    // CREATE RECIPE (Authorization kaldırıldı)
    @PostMapping
    public RecipeResponse createRecipe(@RequestBody RecipeRequest request) {

        // Şimdilik test amaçlı userId = 1
        Long userId = 1L;

        return recipeService.createRecipe(request, userId);
    }

    // GET ALL RECIPES
    @GetMapping
    public List<RecipeResponse> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    // GET RECIPE BY ID
    @GetMapping("/{id}")
    public RecipeResponse getRecipe(@PathVariable Long id) {
        return recipeService.getRecipeResponseById(id);
    }

    // DELETE RECIPE (Authorization kaldırıldı)
    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Long id) {

        // Şimdilik userId = 1
        Long userId = 1L;

        recipeService.deleteRecipe(id, userId);
    }
}
