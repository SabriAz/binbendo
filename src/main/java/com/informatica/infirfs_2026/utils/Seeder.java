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
                "De langverwachte opvolger van de originele Nintendo Switch. Met een groter OLED-scherm, krachtigere processor en verbeterde Joy-Cons tilt de Switch 2 de handheld gaming ervaring naar een compleet nieuw niveau. Geschikt voor thuis en onderweg.",
                499.99,
                "/assets/images/nintendo_switch_2.png",
                category1
        );
        Product product2 = new Product(
                "Nintendo Switch Original",
                "De console die alles veranderde. De originele Nintendo Switch combineert thuisgamen met onderweg spelen op een manier die niemand ooit eerder had gezien. Met een enorme bibliotheek aan geweldige games is dit de ideale keuze voor elke gamer.",
                279.99,
                "/assets/images/nintendo_switch_original.png",
                category1
        );
        Product product3 = new Product(
                "Nintendo Switch Lite",
                "Compacter, lichter en volledig gericht op handheld gaming. De Nintendo Switch Lite is de perfecte reisgenoot voor gamers die altijd en overal willen spelen. Met een ingebouwde controller en langere batterijduur is dit de ideale keuze voor onderweg.",
                199.99,
                "/assets/images/nintendo_switch_lite.png",
                category1
        );
        Product product4 = new Product(
                "Nintendo Switch OLED",
                "Het beste van de originele Switch, nu met een verbluffend 7-inch OLED-scherm met levendige kleuren en diepe contrasten. De verbeterde standaard en bedraad LAN-poort in de dock maken dit de ultieme Switch-ervaring voor thuis en onderweg.",
                349.99,
                "/assets/images/nintendo_switch_oled.png",
                category1
        );

        // Games
        Product product5 = new Product(
                "Zelda: Breath of the Wild",
                "Verken een enorme open wereld vol geheimen, puzzels en avonturen in dit baanbrekende meesterwerk. Als Link ontwaak je in een wereld die hersteld moet worden van de verwoesting van Calamity Ganon. Met volledige vrijheid om te gaan waar je wilt is dit een van de beste games ooit gemaakt.",
                69.99,
                "/assets/images/zelda_breath_of_the_wild.png",
                category2
        );
        Product product6 = new Product(
                "Zelda: Tears of the Kingdom",
                "Het vervolg op Breath of the Wild tilt alles naar een hoger niveau. Zwef door de lucht, duik in de ondergrond en bouw je eigen machines met het revolutionaire Ultrahand-systeem. Hyrule is groter en gevarieerder dan ooit in dit epische avontuur.",
                69.99,
                "/assets/images/zelda_tears_of_the_kingdom.png",
                category2
        );
        Product product7 = new Product(
                "Super Mario Odyssey",
                "Reis met Mario en zijn nieuwe vriend Cappy door kleurrijke koninkrijken over de hele wereld. Gooi je hoed om vijanden over te nemen en geheimen te ontdekken in dit fantastische 3D-platformavontuur vol verassingen en creativiteit.",
                59.99,
                "/assets/images/super_mario_odyssey.png",
                category2
        );
        Product product8 = new Product(
                "Super Smash Bros Ultimate",
                "Het grootste Smash Bros ooit. Met meer dan 80 vechters, 100 speelvelden en duizenden muzieknummers is dit de ultieme verzameling van Nintendo's rijke geschiedenis. Vecht solo, lokaal of online tegen vrienden en spelers van over de hele wereld.",
                59.99,
                "/assets/images/super_smash_bros.png",
                category2
        );
        Product product9 = new Product(
                "Mario Kart 8 Deluxe",
                "De snelste en meest complete Mario Kart ooit. Race met je favoriete Nintendo-karakters op meer dan 48 unieke circuits. Met lokale en online multiplayer voor maximaal 12 spelers is dit het perfecte feestspel voor vrienden en familie.",
                59.99,
                "/assets/images/mario_kart_8_deluxe.png",
                category2
        );
        Product product10 = new Product(
                "Animal Crossing: New Horizons",
                "Ontvlucht de drukte van het dagelijks leven en bouw je eigen paradijs op een verlaten eiland. Vang insecten, vis, decoreer je huis en ontvang vrienden op jouw unieke eiland. Met dagelijkse updates en seizoenen is er altijd iets nieuws te ontdekken.",
                59.99,
                "/assets/images/animal_crossing_new_horizons.png",
                category2
        );
        Product product11 = new Product(
                "Pokémon Scarlet",
                "Ontdek de open wereld van Paldea in het meest ambitieuze Pokémon-avontuur ooit. Vang, train en battle met honderden Pokémon in een wereld die je in elke volgorde kunt verkennen. Met coöp multiplayer kun je samen met vrienden op avontuur gaan.",
                59.99,
                "/assets/images/pokemon_scarlet.png",
                category2
        );

        // Amiibo
        Product product12 = new Product(
                "Amiibo Link - Tears of the Kingdom",
                "Het iconische Link amiibo uit Breath of the Wild. Tik hem aan op je Switch voor exclusieve in-game beloningen zoals zeldzame wapens en materialen. Een must-have voor elke Zelda-fan en een prachtig verzamelobject voor op de plank.",
                15.99,
                "/assets/images/amiibo_link_totk.png",
                category3
        );
        Product product13 = new Product(
                "Amiibo Mario",
                "De klassieke Mario amiibo in zijn vertrouwde rode outfit. Compatibel met tientallen Nintendo Switch-games voor unieke bonussen en extra content. Een tijdloze toevoeging aan elke amiibo-collectie.",
                15.99,
                "/assets/images/amiibo_mario.png",
                category3
        );
        Product product14 = new Product(
                "Amiibo Pikachu",
                "De populairste Pokémon ter wereld als amiibo. Unlock exclusieve content in Super Smash Bros Ultimate en andere compatibele games. Met zijn schattige uiterlijk is dit amiibo zowel een speelgoed als een collector's item.",
                15.99,
                "/assets/images/amiibo_pikachu.png",
                category3
        );

        // Accessories
        Product product15 = new Product(
                "Nintendo Switch Pro Controller",
                "De premium controller voor de meest comfortabele Nintendo Switch-ervaring. Met ergonomische knoppen, gyroscoopbesturing, HD-rumble en een batterijduur van meer dan 40 uur is dit de ideale keuze voor lange gamesessies thuis.",
                69.99,
                "/assets/images/pro_controller.png",
                category4
        );
        Product product16 = new Product(
                "Joy-Con Set Neon Rood/Blauw",
                "Het iconische Joy-Con duo in opvallend neon rood en blauw. Gebruik ze samen als één controller, apart als twee kleine controllers of bevestig ze aan de Switch voor onderweg. Met HD-rumble en IR-camera voor een unieke speelbeleving.",
                79.99,
                "/assets/images/joy_con_neon.png",
                category4
        );
        Product product17 = new Product(
                "Mario Kart Stuurwiel - 2 pack",
                "Maak je Mario Kart-ervaring nog realistischer met deze officiële stuurwielen voor de Joy-Cons. Klik je Joy-Con erin en stuur alsof je echt achter het stuur zit. Ideaal voor families en vrienden die samen willen racen.",
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
