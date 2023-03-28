package com.example.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DiscountDto {
    private Long discountId;
    private String name;
    private String startDate;
    private String endDate;
    private String method;
    private int value;
    private String unit;
    private List<Long> listProductId;
}
