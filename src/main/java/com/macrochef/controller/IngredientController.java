package com.macrochef.controller;

import com.macrochef.dto.IngredientRequest;
import com.macrochef.entity.Ingredient;
import com.macrochef.service.IngredientService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @PostMapping
    public Ingredient createIngredient(@RequestBody IngredientRequest req) {
        return ingredientService.createIngredient(req);
    }
}
