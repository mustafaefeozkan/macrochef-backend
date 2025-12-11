package com.macrochef.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddIngredientToRecipeRequest {
    private Long ingredientId;
    private Double amountInGrams;
}
