package com.macrochef.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientRequest {
    private String name;
    private Double calories100g;
    private Double protein100g;
    private Double carbs100g;
    private Double fat100g;
}
