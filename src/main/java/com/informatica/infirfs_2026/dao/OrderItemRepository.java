package com.informatica.infirfs_2026.dao;

import com.informatica.infirfs_2026.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
