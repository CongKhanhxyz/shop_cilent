package com.example.shopfe.model;

import lombok.Data;

@Data
public class Product {
    private Long productId;
    private String productName;
    private double oldPrice;
    private int percentDiscount;
    private String urlImage;
    private int quantity;
    private int status;
    private String shortDescription;
    private double newPrice;
    private String categoryName;
}
