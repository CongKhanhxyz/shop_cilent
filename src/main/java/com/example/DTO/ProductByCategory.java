package com.example.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ProductByCategory {
    private Long categoryId;
    private String categoryName;
    private List<Long> listProductId;
    private int size;
}
