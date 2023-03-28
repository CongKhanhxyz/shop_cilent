package com.example.DTO;

import lombok.Data;

import java.util.List;

@Data
public class DiscountProductDto {
    private Long discountId;
    private String name;
    private String startDate;
    private String endDate;
    private String method;
    private int value;
    private String unit;
    private List<String> category;
    private List<String> products;
}
