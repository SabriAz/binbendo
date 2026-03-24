package com.informatica.infirfs_2026.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public class ProductDTO {
    public String name;
    public String description;
    public double price;

    @JsonAlias("category_id")
    public long categoryId;

    public ProductDTO(String name, String description, double price, long categoryId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
    }
}
