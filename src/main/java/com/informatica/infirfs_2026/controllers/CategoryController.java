package com.informatica.infirfs_2026.controllers;

import com.informatica.infirfs_2026.services.CategoryService;
import com.informatica.infirfs_2026.dto.CategoryDTO;
import com.informatica.infirfs_2026.models.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(this.categoryService.getAllCategories());
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody CategoryDTO categoryDTO) {
        this.categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok("Category created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable long id, @RequestBody CategoryDTO categoryDTO) {
        this.categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok("Category updated with id " + id);
    }
}
