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
import java.util.Optional;

@Component
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    //Check if there is any products to fetch before returning list to user
    public List<Product> getAllProducts(){
        if (productRepository.findAll().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " No products found");
        }

        return this.productRepository.findAll();
    }

    // Checking if product exists before returning product info to user
    public Product getProductById(long id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return product.get();
        }else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
    }

    // Checking if price is greater than 0 to make product and if category exists before making new product
    public void createProduct(ProductDTO productDTO) {
        if (productDTO.price <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price must be greater than 0");
        }
        Optional<Category> optionalCategory = this.categoryRepository.findById(productDTO.categoryId);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            Product product = new Product(
                    productDTO.name,
                    productDTO.description,
                    productDTO.price,
                    category
            );
            this.productRepository.save(product);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
    }

    // Checking if price is greater than 0, category exists and if product is found in the first place
    public void updateProductById(long id, ProductDTO productDTO) {
        if (productDTO.price <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price must be greater than 0");
        }
        Optional<Product> optionalProduct = this.productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product updatedProduct = optionalProduct.get();

            updatedProduct.setName(productDTO.name);
            updatedProduct.setDescription(productDTO.description);
            updatedProduct.setPrice(productDTO.price);

            if (categoryRepository.findById(productDTO.categoryId).isPresent()) {
                updatedProduct.setCategory(categoryRepository.findById(productDTO.categoryId).get());
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
            }
            this.productRepository.save(updatedProduct);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product id not found");
        }
    }

    public void deleteProductById(long id) {
        if (!this.productRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        this.productRepository.deleteById(id);
    }
}
