package com.example.DTO;

import lombok.Data;

@Data
public class OrderStatusUpdate {
    private Long orderId;
    private Integer status;
}
