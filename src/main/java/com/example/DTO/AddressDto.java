package com.example.DTO;


import lombok.Data;

@Data
public class AddressDto {

    private Long addressId;
    private String detailAddress;
    private Long userId;
    private String wardName;
    private String districtName;
    private String provinceName;
    private int typeAddress;
    private String fullnameRecive;
    private String phone;
    private int defaultAddress;
}
