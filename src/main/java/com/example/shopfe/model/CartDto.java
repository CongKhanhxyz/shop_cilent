package com.example.shopfe.model;

import lombok.Data;

@Data
public class CartDto {

    private Long userId;

    private Long productId;

    private String urlImage;

    private String productName;

    private String categoryName;

    private int quantity;

    private double newPrice;

    private int total;
}
