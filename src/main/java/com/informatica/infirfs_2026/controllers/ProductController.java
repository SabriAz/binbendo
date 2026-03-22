package com.informatica.infirfs_2026.controllers;

import com.informatica.infirfs_2026.dao.ProductDAO;
import com.informatica.infirfs_2026.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductDAO productDAO;

    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return this.productDAO.getAllProducts();
    }

}
