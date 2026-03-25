package com.informatica.infirfs_2026.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public class ProductDTO {
    public String name;
    public String description;
    public double price;

    @JsonAlias("category_id")
    public long categoryId;
}
