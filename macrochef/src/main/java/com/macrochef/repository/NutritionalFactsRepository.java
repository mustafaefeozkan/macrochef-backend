package com.macrochef.repository;

import com.macrochef.entity.NutritionalFacts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NutritionalFactsRepository extends JpaRepository<NutritionalFacts, Long> {
    Optional<NutritionalFacts> findByRecipeId(Long recipeId);
}
