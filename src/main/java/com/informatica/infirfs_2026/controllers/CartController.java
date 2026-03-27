package com.informatica.infirfs_2026.controllers;

import com.informatica.infirfs_2026.dto.CartItemDTO;
import com.informatica.infirfs_2026.dto.PatchCartItemDTO;
import com.informatica.infirfs_2026.models.Cart;
import com.informatica.infirfs_2026.models.CustomUser;
import com.informatica.infirfs_2026.models.Product;
import com.informatica.infirfs_2026.models.Role;
import com.informatica.infirfs_2026.services.CartService;
import com.informatica.infirfs_2026.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<Cart> getCart() {
        Cart cart = this.cartService.getCartByUser();
        return ResponseEntity.ok(cart);
    }

    @PostMapping
    public ResponseEntity<String> addProductToCart(@RequestBody CartItemDTO cartItemDTO) {
        Cart cart = this.cartService.getCartByUser();
        this.cartService.addProductToCart(cart, cartItemDTO);
        return ResponseEntity.ok("Product added to cart");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateQuantityCartItem(@PathVariable long id, @RequestBody PatchCartItemDTO patchCartItemDTO) {
        this.cartService.updateQuantityCartItem(id, patchCartItemDTO);
        return ResponseEntity.ok("Updated quantity of cart item with id " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCartItem (@PathVariable long id) {
        this.cartService.deleteCartItem(id);
        return ResponseEntity.ok("Deleted cart item with id " + id);
    }

    @DeleteMapping
    public ResponseEntity<String> clearCart() {
        this.cartService.clearCart();
        return ResponseEntity.ok("Deleted all cart items");
    }
}
