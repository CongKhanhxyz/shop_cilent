package com.example.DTO;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {
    private Long userId;
    private String listProductId;
    private String color;
    private String size;
    private String methodPayment;
    private Long addressId;
    private Double totalPrice;
    private Long productId;
    private int quantity;
}
