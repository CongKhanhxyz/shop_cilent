package com.example.DTO;


import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderDto {

    private Long orderId;
    private Long productId;
    private UserDto userDto;
    private String productName;
    private Double newPriceProduct;
    private String urlImage;
    private int quantity;
    private int status;
    private LocalDate createdDate;
    private LocalDate shipDate;
    private String color;
    private String size;
    private String methodPayment;
    private Double totalPrice;
    private String address;
    private String phone;
    private String nameRecived;
}
