package com.informatica.infirfs_2026.dao;

import com.informatica.infirfs_2026.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
