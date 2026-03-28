package com.informatica.infirfs_2026.controllers;

import com.informatica.infirfs_2026.models.Order;
import com.informatica.infirfs_2026.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = this.orderService.getOrdersByUser();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable long id) {
        Order order = this.orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<String> placeOrder() {
        this.orderService.placeOrder();
        return ResponseEntity.ok("Order placed successfully");
    }
}
