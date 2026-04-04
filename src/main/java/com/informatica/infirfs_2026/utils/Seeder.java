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
        Category category3 = new Category("Amiibo");
        Category category4 = new Category("Accessories");

        this.categoryRepository.save(category1);
        this.categoryRepository.save(category2);
        this.categoryRepository.save(category3);
        this.categoryRepository.save(category4);


        Product product1 = new Product(
                "Nintendo Switch 2",
                "The long-awaited successor to the original Nintendo Switch. With a larger OLED screen, more powerful processor and improved Joy-Cons, the Switch 2 takes the handheld gaming experience to a whole new level. Perfect for at home and on the go.",
                499.99,
                "/assets/images/nintendo_switch_2.png",
                category1
        );
        Product product2 = new Product(
                "Nintendo Switch Original",
                "The console that changed everything. The original Nintendo Switch combines home gaming with on-the-go play in a way no one had ever seen before. With an enormous library of great games, this is the ideal choice for every gamer.",
                279.99,
                "/assets/images/nintendo_switch_original.png",
                category1
        );
        Product product3 = new Product(
                "Nintendo Switch Lite",
                "More compact, lighter and fully focused on handheld gaming. The Nintendo Switch Lite is the perfect travel companion for gamers who want to play anytime, anywhere. With a built-in controller and longer battery life, this is the ideal choice for on the go.",
                199.99,
                "/assets/images/nintendo_switch_lite.png",
                category1
        );
        Product product4 = new Product(
                "Nintendo Switch OLED",
                "The best of the original Switch, now with a stunning 7-inch OLED screen with vibrant colors and deep contrasts. The improved stand and wired LAN port in the dock make this the ultimate Switch experience for home and on the go.",
                349.99,
                "/assets/images/nintendo_switch_oled.png",
                category1
        );
        Product product5 = new Product(
                "Zelda: Breath of the Wild",
                "Explore a vast open world full of secrets, puzzles and adventure in this groundbreaking masterpiece. As Link you awaken in a world that must be restored from the devastation of Calamity Ganon. With complete freedom to go wherever you want, this is one of the greatest games ever made.",
                69.99,
                "/assets/images/zelda_breath_of_the_wild.png",
                category2
        );
        Product product6 = new Product(
                "Zelda: Tears of the Kingdom",
                "The sequel to Breath of the Wild takes everything to the next level. Soar through the sky, dive into the underground and build your own machines with the revolutionary Ultrahand system. Hyrule is bigger and more varied than ever in this epic adventure.",
                69.99,
                "/assets/images/zelda_tears_of_the_kingdom.png",
                category2
        );
        Product product7 = new Product(
                "Super Mario Odyssey",
                "Travel with Mario and his new friend Cappy through colorful kingdoms around the world. Throw your hat to take over enemies and discover secrets in this fantastic 3D platformer full of surprises and creativity.",
                59.99,
                "/assets/images/super_mario_odyssey.png",
                category2
        );
        Product product8 = new Product(
                "Super Smash Bros Ultimate",
                "The biggest Smash Bros ever. With over 80 fighters, 100 stages and thousands of music tracks, this is the ultimate celebration of Nintendo's rich history. Fight solo, locally or online against friends and players from around the world.",
                59.99,
                "/assets/images/super_smash_bros.png",
                category2
        );
        Product product9 = new Product(
                "Mario Kart 8 Deluxe",
                "The fastest and most complete Mario Kart ever. Race with your favorite Nintendo characters on over 48 unique circuits. With local and online multiplayer for up to 12 players, this is the perfect party game for friends and family.",
                59.99,
                "/assets/images/mario_kart_8_deluxe.png",
                category2
        );
        Product product10 = new Product(
                "Animal Crossing: New Horizons",
                "Escape the hustle of daily life and build your own paradise on a deserted island. Catch bugs, fish, decorate your home and invite friends to your unique island. With daily updates and seasons, there is always something new to discover.",
                59.99,
                "/assets/images/animal_crossing_new_horizons.png",
                category2
        );
        Product product11 = new Product(
                "Pokémon Scarlet",
                "Discover the open world of Paldea in the most ambitious Pokémon adventure ever. Catch, train and battle with hundreds of Pokémon in a world you can explore in any order. With co-op multiplayer you can go on adventures together with friends.",
                59.99,
                "/assets/images/pokemon_scarlet.png",
                category2
        );
        Product product12 = new Product(
                "Amiibo Link - Tears of the Kingdom",
                "The iconic Link amiibo from Tears of the Kingdom. Tap it on your Switch for exclusive in-game rewards such as rare weapons and materials. A must-have for every Zelda fan and a beautiful collectible to display on your shelf.",
                15.99,
                "/assets/images/amiibo_link_totk.png",
                category3
        );
        Product product13 = new Product(
                "Amiibo Mario",
                "The classic Mario amiibo in his iconic red outfit. Compatible with dozens of Nintendo Switch games for unique bonuses and extra content. A timeless addition to any amiibo collection.",
                15.99,
                "/assets/images/amiibo_mario.png",
                category3
        );
        Product product14 = new Product(
                "Amiibo Pikachu",
                "The world's most popular Pokémon as an amiibo. Unlock exclusive content in Super Smash Bros Ultimate and other compatible games. With its cute appearance, this amiibo is both a toy and a collector's item.",
                15.99,
                "/assets/images/amiibo_pikachu.png",
                category3
        );
        Product product15 = new Product(
                "Nintendo Switch Pro Controller",
                "The premium controller for the most comfortable Nintendo Switch experience. With ergonomic buttons, gyroscope controls, HD rumble and a battery life of over 40 hours, this is the ideal choice for long gaming sessions at home.",
                69.99,
                "/assets/images/pro_controller.png",
                category4
        );
        Product product16 = new Product(
                "Joy-Con Set Neon Red/Blue",
                "The iconic Joy-Con duo in striking neon red and blue. Use them together as one controller, separately as two small controllers or attach them to the Switch for on the go. With HD rumble and IR camera for a unique gaming experience.",
                79.99,
                "/assets/images/joy_con_neon.png",
                category4
        );
        Product product17 = new Product(
                "Mario Kart Steering Wheel - 2 pack",
                "Make your Mario Kart experience even more realistic with these official steering wheels for the Joy-Cons. Snap your Joy-Con in and steer as if you are really behind the wheel. Ideal for families and friends who want to race together.",
                19.99,
                "/assets/images/steering_wheel.png",
                category4
        );

        this.productRepository.save(product1);
        this.productRepository.save(product2);
        this.productRepository.save(product3);
        this.productRepository.save(product4);
        this.productRepository.save(product5);
        this.productRepository.save(product6);
        this.productRepository.save(product7);
        this.productRepository.save(product8);
        this.productRepository.save(product9);
        this.productRepository.save(product10);
        this.productRepository.save(product11);
        this.productRepository.save(product12);
        this.productRepository.save(product13);
        this.productRepository.save(product14);
        this.productRepository.save(product15);
        this.productRepository.save(product16);
        this.productRepository.save(product17);
    }
}
