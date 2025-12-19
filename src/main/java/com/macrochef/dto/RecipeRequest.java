package com.macrochef.dto;

import lombok.Data;
import java.util.List;

@Data
public class RecipeRequest {
    private String title;
    private String description;
    private String instructions;
    private String imageUrl;
    private Long categoryId;


    private List<RecipeIngredientRequest> ingredients;
}