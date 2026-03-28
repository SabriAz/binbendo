package com.informatica.infirfs_2026.services;

import com.informatica.infirfs_2026.dao.OrderRepository;
import com.informatica.infirfs_2026.models.CustomUser;
import com.informatica.infirfs_2026.models.Order;
import com.informatica.infirfs_2026.models.Role;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;

    public OrderService(OrderRepository orderRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
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
}
