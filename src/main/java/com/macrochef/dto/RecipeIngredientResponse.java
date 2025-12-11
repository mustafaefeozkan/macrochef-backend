package com.macrochef.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeIngredientResponse {
    private Long ingredientId;
    private String ingredientName;
    private Double amountInGrams;
}
