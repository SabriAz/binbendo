package com.informatica.infirfs_2026.services;

import com.informatica.infirfs_2026.dao.CategoryRepository;
import com.informatica.infirfs_2026.dao.ProductRepository;
import com.informatica.infirfs_2026.dto.CategoryDTO;
import com.informatica.infirfs_2026.models.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void createCategory(CategoryDTO categoryDTO) {
        Category category = new Category(categoryDTO.name);
        this.categoryRepository.save(category);
    }

    public void updateCategoryById(long id, CategoryDTO categoryDTO) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category updatedCategory = optionalCategory.get();
            updatedCategory.setName(categoryDTO.name);
            this.categoryRepository.save(updatedCategory);
        }
    }

    public void deleteCategoryById(long id) {
        if (!this.categoryRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
        else if (this.productRepository.existsByCategoryId(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category still has products");
        }
        this.categoryRepository.deleteById(id);
    }
}
