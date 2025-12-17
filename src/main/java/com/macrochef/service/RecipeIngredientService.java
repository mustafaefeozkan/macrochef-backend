package com.macrochef.service;

import com.macrochef.dto.AddIngredientToRecipeRequest;
import com.macrochef.dto.RecipeIngredientResponse;
import com.macrochef.entity.Ingredient;
import com.macrochef.entity.Recipe;
import com.macrochef.entity.RecipeIngredient;
import com.macrochef.repository.IngredientRepository;
import com.macrochef.repository.RecipeIngredientRepository;
import com.macrochef.repository.RecipeRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeIngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;

    public RecipeIngredientResponse addIngredient(Long recipeId, AddIngredientToRecipeRequest req) {

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        Ingredient ingredient = ingredientRepository.findById(req.getIngredientId())
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));

        RecipeIngredient ri = new RecipeIngredient();
        ri.setRecipe(recipe);
        ri.setIngredient(ingredient);
        ri.setAmountInGrams(req.getAmountInGrams());

        recipeIngredientRepository.save(ri);



        RecipeIngredientResponse res = new RecipeIngredientResponse();
        res.setIngredientId(ingredient.getId());
        res.setIngredientName(ingredient.getName());
        res.setAmountInGrams(req.getAmountInGrams());

        return res;
    }
}
