package com.macrochef.controller;

import com.macrochef.entity.Recipe;
import com.macrochef.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor

public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("/{recipeId}")
    public void toggleFavorite(@PathVariable Long recipeId) {
        favoriteService.toggleFavorite(recipeId);
    }

    @GetMapping
    public Set<Recipe> getFavorites() {
        return favoriteService.getMyFavorites();
    }
}