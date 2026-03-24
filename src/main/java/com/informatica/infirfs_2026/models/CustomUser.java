package com.informatica.infirfs_2026.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "custom_user")
public class CustomUser {

    @Id
    @GeneratedValue
    private long id;

    private String email;

    private String password;

    public CustomUser(String email, String password) {
        this.email = email;
        this.password = password;
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
}
