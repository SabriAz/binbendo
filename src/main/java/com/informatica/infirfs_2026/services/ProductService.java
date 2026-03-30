package com.informatica.infirfs_2026.services;

import com.informatica.infirfs_2026.dao.CategoryRepository;
import com.informatica.infirfs_2026.dao.ProductRepository;
import com.informatica.infirfs_2026.dto.ProductDTO;
import com.informatica.infirfs_2026.models.Category;
import com.informatica.infirfs_2026.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    //Check if there is any products to fetch before returning list to user
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products found");
        }
        return products;
    }

    // Checking if product exists before returning product info to user
    public Product getProductById(long id){
        return productRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    // Gets products by category for filtering categories in the website
    // Checks if category exists, if category has no products frontend should handle this, not worth a whole error
    public List<Product> getProductsByCategories(List<Long> categoryIds) {
        for (Long id : categoryIds) {
            categoryRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        }
        return productRepository.findByCategoryIdIn(categoryIds);
    }


    public List<Product> getProductsByCategory(Long categoryId) {
        this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        List<Product> products = this.productRepository.findByCategoryId(categoryId);
        if (products.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products found for this category");
        }
        return products;
    }

    // Checking if price is greater than 0 to make product and if category exists before making new product
    public void createProduct(ProductDTO productDTO) {
        if (productDTO.price <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price must be greater than 0");
        }
        Category category = categoryRepository.findById(productDTO.categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        Product product = new Product(
                productDTO.name,
                productDTO.description,
                productDTO.price,
                productDTO.imageUrl,
                category
        );
        productRepository.save(product);
    }

    // Checking if price is greater than 0, category exists and if product is found in the first place
    public void updateProductById(long id, ProductDTO productDTO) {
        if (productDTO.price <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price must be greater than 0");
        }
        Product product = this.productRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        Category category = this.categoryRepository.findById(productDTO.categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        product.setName(productDTO.name);
        product.setDescription(productDTO.description);
        product.setPrice(productDTO.price);
        product.setCategory(category);
        productRepository.save(product);
    }

    public void deleteProductById(long id) {
        if (!this.productRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        this.productRepository.deleteById(id);
    }
}
