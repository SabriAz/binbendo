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
        Category category2 = new Category("Zelda Games");
        Category category3 = new Category("Mario Games");

        this.categoryRepository.save(category1);
        this.categoryRepository.save(category2);
        this.categoryRepository.save(category3);

        Product product1 = new Product(
                "Nintendo Switch 2",
                "De gloednieuwe console waar je op wacht.",
                499.99,
                "/assets/images/nintendo_switch_2.png",
                category1
        );
        Product product2 = new Product(
                "Nintendo Switch Original",
                "De super mooie zelda game alleen op Nintendo Swtich.",
                69.99,
                "/assets/images/nintendo_switch_original.png",
                category1
        );
        Product product3 = new Product(
                "Nintendo Switch Lite",
                "De lichte versie van de oude vertrouwde original Switch die je al kent.",
                499.99,
                "/assets/images/nintendo_switch_lite.png",
                category1
        );
        Product product4 = new Product(
                "Zelda: Breath of the Wild",
                "De super mooie zelda game alleen op Nintendo Swtich.",
                69.99,
                "/assets/images/zelda_breath_of_the_wild.png",
                category2
        );
        Product product5 = new Product(
                "Super Mario Odyssey",
                "Het avontuurlijke verhaal van mario die door werelden vliegt.",
                59.99,
                "/assets/images/super_mario_odyssey.png",
                category3
        );
        Product product6 = new Product(
                "Super Smash Bros",
                "Vechten met mario en andere Nintendo karakters.",
                59.99,
                "/assets/images/super_smash_bros.png",
                category3
        );
        Product product7 = new Product(
                "Mario kart 8 Deluxe",
                "De ultieme racing game van nintendo om het tegen je vrienden op te nemen.",
                69.99,
                "/assets/images/mario_kart_8_deluxe.png",
                category3
        );

        this.productRepository.save(product1);
        this.productRepository.save(product2);
        this.productRepository.save(product3);
        this.productRepository.save(product4);
        this.productRepository.save(product5);
        this.productRepository.save(product6);
        this.productRepository.save(product7);
    }
}
