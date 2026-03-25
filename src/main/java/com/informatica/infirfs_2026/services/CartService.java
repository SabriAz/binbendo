package com.informatica.infirfs_2026.services;


import com.informatica.infirfs_2026.dao.CartItemRepository;
import com.informatica.infirfs_2026.dao.ProductRepository;
import com.informatica.infirfs_2026.dto.CartItemDTO;
import com.informatica.infirfs_2026.models.Cart;
import com.informatica.infirfs_2026.models.CartItem;
import com.informatica.infirfs_2026.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
public class CartService {
    private ProductRepository productRepository;
    private CartItemRepository cartItemRepository;

    CartService(CartItemRepository cartItemRepository, ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }


    public void addProductToCart(Cart cart, CartItemDTO cartItemDTO) {
        Optional<Product> product = productRepository.findById(cartItemDTO.productId);
        if (product.isPresent()) {
            CartItem cartItem = new CartItem(cart, product.get(), cartItemDTO.quantity);
            cartItemRepository.save(cartItem);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }

    }

}
