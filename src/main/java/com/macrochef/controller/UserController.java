package com.macrochef.controller;

import com.macrochef.dto.UserProfileResponse;
import com.macrochef.entity.User;
import com.macrochef.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macrochef.repository.FavoriteRepository;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class UserController {

    private final UserService userService;
    private final FavoriteRepository favoriteRepository;

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getCurrentUserProfile() {

        User currentUser = userService.getCurrentUser();


        UserProfileResponse response = new UserProfileResponse();
        response.setUsername(currentUser.getUsername());
        response.setEmail(currentUser.getEmail());


        response.setRecipeCount(currentUser.getRecipes() != null ? currentUser.getRecipes().size() : 0);


        response.setJoinedAt(currentUser.getCreatedAt().toLocalDate().toString());

        int totalLikes = favoriteRepository.countFavoritesReceived(currentUser.getId());
        response.setFavoritesReceived(totalLikes);

        return ResponseEntity.ok(response);
    }
}