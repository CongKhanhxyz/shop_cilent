package com.example.shopfe.model;

import lombok.Data;

@Data
public class OrderRequestDto {
    private Long userId;
    private Long productId;
    private int quantity;
}
