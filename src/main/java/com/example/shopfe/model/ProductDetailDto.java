package com.example.shopfe.model;

import lombok.Data;

@Data
public class ProductDetailDto {
    private Long id;
    private String productName;
    private double oldPrice;
    private String urlImage;
    private int quantity;
    private String shortDescription;
    private double newPrice;
    private int percentDiscount;
    private String category;
    private int starCount;
    private String isHalfCount;
    private DiscountDto discount;
    private int starCountTail;
    private int reviewAmount;
    private int soldAmount;
}
