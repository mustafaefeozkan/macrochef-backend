package com.macrochef.controller;

import com.macrochef.entity.Category;
import com.macrochef.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @PostMapping
    public Category create(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @GetMapping
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }


    @GetMapping("/search")
    public List<Category> search(@RequestParam String name) {

        return categoryRepository.findByNameContainingIgnoreCase(name);
    }
}