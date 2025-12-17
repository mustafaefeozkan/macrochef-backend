package com.macrochef.dto;

import lombok.Data;

@Data
public class RecipeNutritionResponse {
    private double totalCalories;
    private double totalProtein;
    private double totalCarbs;
    private double totalFat;
}
