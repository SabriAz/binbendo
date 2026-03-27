package com.informatica.infirfs_2026.utils;

import com.informatica.infirfs_2026.dao.CategoryRepository;
import com.informatica.infirfs_2026.dao.ProductRepository;
import com.informatica.infirfs_2026.dao.UserRepository;
import com.informatica.infirfs_2026.models.Category;
import com.informatica.infirfs_2026.models.CustomUser;
import com.informatica.infirfs_2026.models.Product;
import com.informatica.infirfs_2026.models.Role;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Seeder {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    // PasswordEncoder nodig om een admin user te seeden, zonder de encoder komt het password als plain tekst in de db te staan, dan werkt inloggen niet + het is onveilig.
    private final PasswordEncoder passwordEncoder;

    public Seeder(
            ProductRepository productRepository,
            CategoryRepository categoryRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository  = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        CustomUser customUser = new CustomUser("admin@email.com", passwordEncoder.encode("Admin123!"), Role.ROLE_ADMIN);

        this.userRepository.save(customUser);

        Category category1 = new Category("Consoles");
        Category category2 = new Category("Games");

        this.categoryRepository.save(category1);
        this.categoryRepository.save(category2);

        Product product1 = new Product("Nintendo switch 2", "De gloednieuwe console waar je op wacht", 499.99, category1);
        Product product2 = new Product("Zelda: Breath of the Wild", "De super mooie zelda game alleen op Nintendo Swtich", 69.99, category2);


        this.productRepository.save(product1);
        this.productRepository.save(product2);



    }
}
