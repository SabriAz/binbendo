package com.informatica.infirfs_2026.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private CustomUser customUser;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> items;



}
