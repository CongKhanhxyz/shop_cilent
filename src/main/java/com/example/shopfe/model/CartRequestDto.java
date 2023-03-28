package com.example.shopfe.model;

import lombok.Data;

@Data
public class CartRequestDto {
    private Long userId;
    private Long productId;
    private int quantity;
}
