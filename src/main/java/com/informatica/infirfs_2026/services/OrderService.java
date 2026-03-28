package com.informatica.infirfs_2026.services;

import com.informatica.infirfs_2026.dao.CartItemRepository;
import com.informatica.infirfs_2026.dao.OrderItemRepository;
import com.informatica.infirfs_2026.dao.OrderRepository;
import com.informatica.infirfs_2026.models.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserService userService;
    private final CartService cartService;
    private final CartItemRepository cartItemRepository;

    public OrderService(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            UserService userService,
            CartService cartService,
            CartItemRepository cartItemRepository
    ) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.userService = userService;
        this.cartService = cartService;
        this.cartItemRepository = cartItemRepository;
    }

    public List<Order> getOrdersByUser() {
        CustomUser customUser = this.userService.getUserByEmail();
        if (customUser.getRole() == Role.ROLE_ADMIN) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Admins cannot use this function");
        }
        return customUser.getOrders();
    }

    public Order getOrderById(long id) {
        Order order = this.orderRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
        CustomUser customUser = this.userService.getUserByEmail();
        if (order.getCustomUser().getId() != customUser.getId()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This order does not belong to you");
        }
        return order;
    }


    public void placeOrder() {
        CustomUser customUser = this.userService.getUserByEmail();
        Cart cart = this.cartService.getCartByUser();

        if (cart.getItems().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart is empty");
        }

        Order order = new Order(customUser, new Date(), cart.getTotalPrice());
        orderRepository.save(order);

        cart.getItems().forEach(cartItem -> {
            OrderItem orderItem = new OrderItem(
                    order,
                    cartItem.getProduct(),
                    cartItem.getQuantity(),
                    cartItem.getProduct().getPrice()
            );
            this.orderItemRepository.save(orderItem);
        });
        cartItemRepository.deleteAll(cart.getItems());
    }
}
