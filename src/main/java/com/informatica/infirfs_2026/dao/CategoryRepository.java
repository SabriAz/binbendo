package com.informatica.infirfs_2026.dao;

import com.informatica.infirfs_2026.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
