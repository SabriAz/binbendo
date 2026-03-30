package com.informatica.infirfs_2026.dao;

import com.informatica.infirfs_2026.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
