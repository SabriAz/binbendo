package com.informatica.infirfs_2026.services;


import com.informatica.infirfs_2026.dao.CartItemRepository;
import com.informatica.infirfs_2026.dao.ProductRepository;
import com.informatica.infirfs_2026.dto.CartItemDTO;
import com.informatica.infirfs_2026.dto.PatchCartItemDTO;
import com.informatica.infirfs_2026.models.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
public class CartService {
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final UserService userService;

    CartService(CartItemRepository cartItemRepository, ProductRepository productRepository, UserService userService) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userService = userService;
    }

    public Cart getCartByUser() {
        CustomUser customUser = this.userService.getUserByEmail();
        if (customUser.getRole() == Role.ROLE_ADMIN) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Admins cannot use this function");
        }
        return customUser.getCart();
    }


    public void addProductToCart(Cart cart, CartItemDTO cartItemDTO) {
        if (cartItemDTO.quantity <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantity must be greater than 0");
        }
        Optional<Product> product = productRepository.findById(cartItemDTO.productId);
        if (product.isPresent()) {
            CartItem cartItem = new CartItem(cart, product.get(), cartItemDTO.quantity);
            cartItemRepository.save(cartItem);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
    }

    // Used for the patch mapping in cart controller for changing quantity of specific cart item
    public void updateQuantityCartItem(long id, PatchCartItemDTO patchCartItemDTO) {
        if (patchCartItemDTO.quantity <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantity must be greater than 0");
        }
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(id);
        if (optionalCartItem.isPresent()) {

            CartItem cartItem = optionalCartItem.get();
            CustomUser customUser = this.userService.getUserByEmail();
            if (cartItem.getCart().getCustomUser().getId() != customUser.getId()) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This cart item does not belong to you");
            }
            else if (cartItem.getQuantity() == patchCartItemDTO.quantity) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Quantity is already " + patchCartItemDTO.quantity);
            }
            cartItem.setQuantity(patchCartItemDTO.quantity);
            cartItemRepository.save(cartItem);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
    }

    public void deleteCartItem(long id) {
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(id);
        if (optionalCartItem.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart item not found");
        }
        CartItem cartItem = optionalCartItem.get();
        CustomUser customUser = this.userService.getUserByEmail();
        if (cartItem.getCart().getCustomUser().getId() != customUser.getId()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This cart item does not belong to you");
        }
        this.cartItemRepository.deleteById(id);
    }

    public void clearCart() {
        Cart cart = getCartByUser();
        this.cartItemRepository.deleteAll(cart.getItems());
    }
}
