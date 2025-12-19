package com.macrochef.dto;

import lombok.Data;

@Data
public class UserProfileResponse {
    private String username;
    private String email;
    private int recipeCount;
    private String joinedAt;
    private int favoritesReceived;
}