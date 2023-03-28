package com.example.shopfe.model;


import lombok.Data;

@Data
public class AddressDto {

    private Long addressId;
    private String detailAddress;
    private Long userId;
    private String wardName;
    private String districtName;
    private String provinceName;
    private String phone;
    private String fullnameRecive;
}
