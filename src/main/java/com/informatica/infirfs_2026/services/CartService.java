package com.informatica.infirfs_2026.services;


import com.informatica.infirfs_2026.dao.CartItemRepository;
import com.informatica.infirfs_2026.dao.ProductRepository;
import com.informatica.infirfs_2026.dto.CartItemDTO;
import com.informatica.infirfs_2026.dto.PatchCartItemDTO;
import com.informatica.infirfs_2026.models.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

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

    // Getting cart by user email, if user is admin they cant use cart
    // Admin accounts should mainly be used for admin panel, this way admin account cant be abused
    public Cart getCartByUser() {
        CustomUser customUser = this.userService.getUserByEmail();
        if (customUser.getRole() == Role.ROLE_ADMIN) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Admins cannot use this function");
        }
        return customUser.getCart();
    }

    // Adding products to the cart, again quantity has to be more than 0 or else totalPrice can be negative
    // Gets authorized in Security Config
    public void addProductToCart(Cart cart, CartItemDTO cartItemDTO) {
        if (cartItemDTO.quantity <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantity must be greater than 0");
        }
        Product product = this.productRepository.findById(cartItemDTO.productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        CartItem cartItem = new CartItem(
                cart,
                product,
                cartItemDTO.quantity);

        this.cartItemRepository.save(cartItem);
    }

    // Used for the patch mapping in cart controller for changing quantity of specific cart item
    // Quantity cant be the same as current quantity or <=0 for invalid quantity checks
    // Also a user check to see if cart item belongs to said user
    public void updateQuantityCartItem(long id, PatchCartItemDTO patchCartItemDTO) {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart item not found"));

        CustomUser customUser = this.userService.getUserByEmail();
        if (cartItem.getCart().getCustomUser().getId() != customUser.getId()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This cart item does not belong to you");
        }

        if (patchCartItemDTO.quantity <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantity must be greater than 0");
        }

        if (patchCartItemDTO.quantity == cartItem.getQuantity()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Quantity is already " + patchCartItemDTO.quantity);
        }

        cartItem.setQuantity(patchCartItemDTO.quantity);
        this.cartItemRepository.save(cartItem);
    }

    // Deletes specific cart item from cart if exists and if cart item actually belongs to user
    public void deleteCartItem(long id) {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart item not found"));

        CustomUser customUser = this.userService.getUserByEmail();
        if (cartItem.getCart().getCustomUser().getId() != customUser.getId()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This cart item does not belong to you");
        }

        cartItemRepository.delete(cartItem);
    }

    // Clears all cart items from Cart, gets authorized in Security Config
    public void clearCart() {
        Cart cart = getCartByUser();
        this.cartItemRepository.deleteAll(cart.getItems());
    }
}
