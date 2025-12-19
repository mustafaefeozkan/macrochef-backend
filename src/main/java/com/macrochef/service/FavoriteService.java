package com.macrochef.service;

import com.macrochef.entity.Recipe;
import com.macrochef.entity.User;
import com.macrochef.repository.RecipeRepository;
import com.macrochef.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;
    private final UserService userService;

    @Transactional
    public void toggleFavorite(Long recipeId) {
        User user = userService.getCurrentUser();
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        // Eğer zaten favorilerde varsa çıkar, yoksa ekle
        if (user.getFavorites().contains(recipe)) {
            user.getFavorites().remove(recipe);
        } else {
            user.getFavorites().add(recipe);
        }

        userRepository.save(user);
    }

    public Set<Recipe> getMyFavorites() {
        User user = userService.getCurrentUser();

        return user.getFavorites();
    }
}