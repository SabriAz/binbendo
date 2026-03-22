package com.informatica.infirfs_2026.utils;

import com.informatica.infirfs_2026.dao.ProductRepository;
import com.informatica.infirfs_2026.models.Product;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Seeder {

    private ProductRepository productRepository;
    public Seeder(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        Product product1 = new Product("Nintendo switch 2", "De gloednieuwe console waar je op wacht", 499.99);
        Product product2 = new Product("Nintendo switch", "De super oude console die je iets minder graag wil", 299.99);

        this.productRepository.save(product1);
        this.productRepository.save(product2);
    }
}
