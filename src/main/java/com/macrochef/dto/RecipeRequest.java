package com.macrochef.dto;

import lombok.Data;

@Data
public class RecipeRequest {
    private String title;
    private String description;
    private String instructions;
    private String imageUrl;
}
