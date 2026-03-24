package com.informatica.infirfs_2026.dao;

import com.informatica.infirfs_2026.models.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<CustomUser, Long> {
    public CustomUser findByEmail(String email);
}
