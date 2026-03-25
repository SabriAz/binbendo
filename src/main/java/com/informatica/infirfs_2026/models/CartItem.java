package com.informatica.infirfs_2026.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class CartItem {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Cart cart;


}
