package com.example.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ProductReport {

    private Long orderId;
    private Long productId;
    private String productName;
    private String urlImage;
    private Long quantity;
    private int status;
    private LocalDate createdDate;
    private Double totalPrice;
    private Long discountId;

    public ProductReport(Long productId, String productName, Long quantity, Double totalPrice) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
//    public ProductReport(Long productId, Long quantity, Double totalPrice) {
//        this.productId = productId;
//        this.quantity = quantity;
//        this.totalPrice = totalPrice;
//    }
}
