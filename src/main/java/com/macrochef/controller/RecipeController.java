package com.macrochef.controller;

import com.macrochef.dto.AddIngredientToRecipeRequest;
import com.macrochef.dto.RecipeIngredientResponse;
import com.macrochef.dto.RecipeRequest;
import com.macrochef.dto.RecipeResponse;
import com.macrochef.service.RecipeIngredientService;
import com.macrochef.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes") // DÜZELTME 1: React /api/recipes diye aradığı için burayı güncelledik
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173") // DÜZELTME 2: React'in (5173) buraya erişmesine izin verdik
public class RecipeController {

    private final RecipeService recipeService;
    private final RecipeIngredientService recipeIngredientService;

    // -------------------------
    // TARİF OLUŞTURMA
    // -------------------------
    @PostMapping
    public ResponseEntity<RecipeResponse> createRecipe(@RequestBody RecipeRequest request) {
        return ResponseEntity.ok(recipeService.createRecipe(request));
    }

    // -------------------------
    // LİSTELEME
    // -------------------------
    @GetMapping
    public ResponseEntity<List<RecipeResponse>> getAllRecipes() {
        // Konsola yazdıralım ki çalıştığını görelim
        System.out.println("RecipeController: Tarifler isteniyor...");
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeResponse> getRecipe(@PathVariable Long id) {
        return ResponseEntity.ok(recipeService.getRecipeResponseById(id));
    }

    // -------------------------
    // GÜNCELLEME
    // -------------------------
    @PutMapping("/{id}")
    public ResponseEntity<RecipeResponse> updateRecipe(
            @PathVariable Long id,
            @RequestBody RecipeRequest request
    ) {
        return ResponseEntity.ok(recipeService.updateRecipe(id, request));
    }

    // -------------------------
    // SİLME
    // -------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }

    // -------------------------
    // TARİFE MALZEME EKLEME
    // -------------------------
    @PostMapping("/{recipeId}/ingredients")
    public ResponseEntity<RecipeIngredientResponse> addIngredient(
            @PathVariable Long recipeId,
            @RequestBody AddIngredientToRecipeRequest request
    ) {
        return ResponseEntity.ok(recipeIngredientService.addIngredient(recipeId, request));
    }
    @GetMapping("/my-recipes")
    public ResponseEntity<List<RecipeResponse>> getMyRecipes() {
        return ResponseEntity.ok(recipeService.getMyRecipes());
    }

}