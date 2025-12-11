package com.macrochef.dto;

import lombok.Data;

@Data
public class RecipeResponse {
    private Long id;
    private String title;
    private String description;
    private String instructions;
    private String imageUrl;
    private String createdAt;
    private String createdBy;
}
