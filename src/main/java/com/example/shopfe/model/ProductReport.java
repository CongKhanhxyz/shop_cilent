package com.example.shopfe.model;


import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductReport {

    private Long orderId;
    private Long productId;
    private String productName;
    private String urlImage;
    private int quantity;
    private int status;
    private LocalDate createdDate;
    private Double totalPrice;
    private Long discountId;
}
