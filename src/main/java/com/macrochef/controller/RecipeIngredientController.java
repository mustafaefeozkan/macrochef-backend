package com.macrochef.controller;

import com.macrochef.dto.AddIngredientToRecipeRequest;
import com.macrochef.dto.RecipeIngredientResponse;
import com.macrochef.service.RecipeIngredientService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipes/{recipeId}/ingredients")
@RequiredArgsConstructor
public class RecipeIngredientController {

    private final RecipeIngredientService recipeIngredientService;

    @PostMapping
    public RecipeIngredientResponse addIngredient(@PathVariable Long recipeId,
                                                  @RequestBody AddIngredientToRecipeRequest req) {
        return recipeIngredientService.addIngredient(recipeId, req);
    }
}
