package com.macrochef.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ingredients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 150)
    private String name;

    // Nutritional values per 100 grams
    private Double calories100g;
    private Double protein100g;
    private Double carbs100g;
    private Double fat100g;

    private String apiId;
    private String apiSource;
}
