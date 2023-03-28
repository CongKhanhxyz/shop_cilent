package com.example.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CartDeleteAll {
    private Long userId;
    private List<Long> listProductId;
}
