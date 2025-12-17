package com.macrochef.dto;

import lombok.Data;

import java.util.List;
import com.macrochef.dto.RecipeIngredientResponse;


@Data
public class RecipeResponse {
    private Long id;
    private String title;
    private String description;
    private String instructions;
    private String imageUrl;
    private String createdAt;
    private String createdBy;

    private double totalCalories;
    private double totalProtein;
    private double totalCarbs;
    private double totalFat;

    private String categoryName;

    private List<RecipeIngredientResponse> ingredients;

}
