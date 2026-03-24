package com.informatica.infirfs_2026.controllers;

import com.informatica.infirfs_2026.dao.CategoryDAO;
import com.informatica.infirfs_2026.dto.CategoryDTO;
import com.informatica.infirfs_2026.models.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryDAO categoryDAO;

    public CategoryController(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(this.categoryDAO.getAllCategories());
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody CategoryDTO categoryDTO) {
        this.categoryDAO.createCategory(categoryDTO);
        return ResponseEntity.ok("Category created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable long id, @RequestBody CategoryDTO categoryDTO) {
        this.categoryDAO.updateCategory(id, categoryDTO);
        return ResponseEntity.ok("Category updated with id " + id);
    }
}
