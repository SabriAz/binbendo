package com.informatica.infirfs_2026.dao;

import com.informatica.infirfs_2026.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //Checks if categories is linked to any products before admin deletes it
    boolean existsByCategoryId(long id);

    List<Product> findByCategoryId(Long categoryId);

    List<Product> findByCategoryIdIn(List<Long> categoryIds);
}
