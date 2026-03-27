package com.informatica.infirfs_2026.dao;

import com.informatica.infirfs_2026.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //Checken of categories gelinked is aan producten voordat admin verwijderd
    boolean existsByCategoryId(long id);
}
