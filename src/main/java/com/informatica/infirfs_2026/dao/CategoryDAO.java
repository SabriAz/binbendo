package com.informatica.infirfs_2026.dao;

import com.informatica.infirfs_2026.dto.CategoryDTO;
import com.informatica.infirfs_2026.models.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryDAO {
    private final CategoryRepository categoryRepository;

    public CategoryDAO(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void createCategory(CategoryDTO categoryDTO) {
        Category category = new Category(categoryDTO.name);
        this.categoryRepository.save(category);
    }

    public void updateCategory(long id, CategoryDTO categoryDTO) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category updatedCategory = optionalCategory.get();
            updatedCategory.setName(categoryDTO.name);

        }

    }
}
