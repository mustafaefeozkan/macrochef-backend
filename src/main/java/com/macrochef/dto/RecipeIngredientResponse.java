package com.macrochef.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeIngredientResponse {
    private Long ingredientId;
    private String ingredientName;
    private Double amountInGrams;

    private double calories;
    private double protein;
    private double carbs;
    private double fat;
}
