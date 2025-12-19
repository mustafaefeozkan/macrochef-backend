package com.macrochef.dto;

import lombok.Data;

@Data
public class RecipeIngredientRequest {

    private Long ingredientId;
    private Double amountInGrams;
}