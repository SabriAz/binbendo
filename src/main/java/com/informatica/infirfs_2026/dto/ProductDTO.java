package com.informatica.infirfs_2026.dto;

public class ProductDTO {
    public String name;
    public String description;
    public double price;

    public ProductDTO(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
