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

    public List<Product> getAllProducts(){
        List<Product> products;
        products = this.productRepository.findAll();
        return products;
    }

    public Product getProductById(long id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return product.get();
        }else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void createProduct(ProductDTO productDTO) {
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category id not found");
        }

    }

    public void updateProduct(long id, ProductDTO productDTO) {
        Optional<Product> optionalProduct = this.productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product updatedProduct = optionalProduct.get();

            updatedProduct.setName(productDTO.name);
            updatedProduct.setDescription(productDTO.description);
            updatedProduct.setPrice(productDTO.price);

            this.productRepository.save(updatedProduct);
        }else{
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Product id not found"
            );
        }


    }
}
