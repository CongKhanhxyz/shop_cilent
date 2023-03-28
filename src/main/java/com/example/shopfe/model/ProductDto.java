package com.example.shopfe.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductDto {

//    private String productName;
//    private double oldPrice;
//    private String urlImage;
//    private int status;
//    private int quantity;
//    private String shortDescription;
//    private String category;
//    private int totalLike;
//    private int soldAmount;
//    private double newPrice;
//    private DiscountDto discount;//
    private Long id;
    private String productName;
    private double oldPrice;
    private MultipartFile urlImage;
    private int quantity;
    private String shortDescription;
    private double newPrice;
    private String category;
    private int starCount;
    private String isHalfCount;
    private int starCountTail;
    private DiscountDto discount;
    private int reviewAmount;
    private int soldAmount;
}
