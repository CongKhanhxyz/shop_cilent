package com.example.DTO;

import lombok.Data;

@Data
public class CartRequestDto {
    private Long userId;
    private Long productId;
    private int quantity;
}
