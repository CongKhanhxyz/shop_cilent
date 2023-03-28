package com.example.DTO;

import lombok.Data;

@Data
public class UserDetailDto {
    private Long userId;
    private String username;
    private String password;
    private String lastname;
    private String firstname;
    private String phone;
    private String urlImageAvatar;
    private String address;
    private String wardName;
}
