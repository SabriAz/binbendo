package com.informatica.infirfs_2026.models;

import jakarta.persistence.*;

@Entity(name = "custom_user")
public class CustomUser {

    @Id
    @GeneratedValue
    private long id;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "customUser")
    private Cart cart;

    public CustomUser(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public CustomUser() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
