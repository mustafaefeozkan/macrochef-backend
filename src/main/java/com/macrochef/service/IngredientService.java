package com.macrochef.service;

import com.macrochef.dto.IngredientRequest;
import com.macrochef.entity.Ingredient;
import com.macrochef.repository.IngredientRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository repo;
    private final ExternalApiService api;

    public Ingredient createIngredient(IngredientRequest req) {

        Ingredient ing = new Ingredient();
        ing.setName(req.getName());

        ing.setCalories100g(0.0);
        ing.setProtein100g(0.0);
        ing.setCarbs100g(0.0);
        ing.setFat100g(0.0);

        // OpenFoodFacts'tan veri çek
        ing = api.fillNutrition(ing);

        return repo.save(ing);
    }
}
