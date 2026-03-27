package com.informatica.infirfs_2026.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Order {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private CustomUser customUser;

    private Date date;

    private double totalPrice;
}
