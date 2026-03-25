package com.informatica.infirfs_2026.controllers;

import com.informatica.infirfs_2026.dto.CartItemDTO;
import com.informatica.infirfs_2026.models.Cart;
import com.informatica.infirfs_2026.models.CustomUser;
import com.informatica.infirfs_2026.models.Product;
import com.informatica.infirfs_2026.services.CartService;
import com.informatica.infirfs_2026.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final UserService userService;

    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Cart> getCart() {
        Cart cart = getCurrentUserCart();
        return ResponseEntity.ok(cart);

    }

    @PostMapping
    public ResponseEntity<String> addProductToCart(@RequestBody CartItemDTO cartItemDTO) {
        Cart cart = getCurrentUserCart();
        this.cartService.addProductToCart(cart, cartItemDTO);
        return ResponseEntity.ok("Product added to cart");

    }

    private Cart getCurrentUserCart() {
        CustomUser customUser = this.userService.getUserByEmail();
        return customUser.getCart();
    }
}
