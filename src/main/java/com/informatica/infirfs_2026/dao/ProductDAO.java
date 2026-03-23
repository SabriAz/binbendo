package com.informatica.infirfs_2026.dao;

import com.informatica.infirfs_2026.dto.ProductDTO;
import com.informatica.infirfs_2026.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Component
public class ProductDAO {
    private final ProductRepository productRepository;

    public ProductDAO(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        List<Product> products;
        products = this.productRepository.findAll();
        return products;
    }

    public void createProduct(ProductDTO productDTO) {
        Product product = new Product(productDTO.name, productDTO.description, productDTO.price);
        this.productRepository.save(product);
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
