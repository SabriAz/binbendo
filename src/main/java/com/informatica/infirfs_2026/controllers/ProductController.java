package com.informatica.infirfs_2026.controllers;

import com.informatica.infirfs_2026.dao.ProductDAO;
import com.informatica.infirfs_2026.dto.ProductDTO;
import com.informatica.infirfs_2026.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductDAO productDAO;

    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = this.productDAO.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO) {
        this.productDAO.createProduct(productDTO);
        return ResponseEntity.ok("Product created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable long id, @RequestBody ProductDTO productDTO) {
        this.productDAO.updateProduct(id, productDTO);
        return ResponseEntity.ok("Updated product with id" + id);
    }

}
