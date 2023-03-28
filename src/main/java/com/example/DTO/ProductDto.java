package com.example.DTO;

import lombok.Data;

import java.sql.Blob;
import java.util.List;

@Data
public class ProductDto {
    private Long id;
    private String productName;
    private double oldPrice;
    private String urlImage;
    private int percentDiscount;
    private int quantity;
    private String shortDescription;
    private String category;
    private int starCount;
    private int starCountTail;
    private int isHalfCount;
    private double newPrice;
    private DiscountDto discount;
    private List<SizeDto> sizes;
    private float soldAmount;
    private int reviewAmount;

}
