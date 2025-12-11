package com.macrochef.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "nutritional_facts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NutritionalFacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 1:1
    @OneToOne
    @JoinColumn(name = "recipe_id", nullable = false, unique = true)
    private Recipe recipe;

    // totals
    private Double totalCalories;
    private Double totalProtein;
    private Double totalCarbs;
    private Double totalFat;

    // extended API fields
    private Double sugar;
    private Double fiber;
    private Double saturatedFat;
    private Double cholesterol;
    private Double sodium;
    private Double potassium;

    private Boolean apiUsed = false;
}
