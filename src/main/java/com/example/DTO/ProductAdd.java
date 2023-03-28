package com.example.DTO;

import lombok.Data;

@Data
public class ProductAdd {
    private Long productId;
    private String productName;
    private double oldPrice;
    private String urlImage;
    private int percentDiscount;
    private int quantity;
    private String shortDescription;
    private String categoryName;
    private int status;
    private int totalLike;
    private int soldAmount;
    private double newPrice;
}
