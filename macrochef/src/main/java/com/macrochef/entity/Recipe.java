package com.macrochef.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "recipes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // USER → RECIPE (N:1)
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // CATEGORY → RECIPE (N:1)
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "LONGTEXT")
    private String instructions;

    private String imageUrl;

    private LocalDateTime createdAt = LocalDateTime.now();

    // --- API FIELDS ---
    private Boolean apiCalculated = false;
    private Double apiMatchScore;
    private String apiRecipeUrl;
    private Integer servings = 1;

    // RECIPE → RECIPE_INGREDIENTS (1:N)
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeIngredient> ingredients;

    // RECIPE → NUTRITIONAL_FACTS (1:1)
    @OneToOne(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private NutritionalFacts nutritionalFacts;

    // RECIPE → COMMENTS (1:N)
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;
}
