package com.example.shopfe.model;

import lombok.Data;

@Data
public class JwtResponseDTO {
    private long id;
    private String accessToken;
    private String tokenType ;
    private String username;
    private String name;
    private String urlImageAvatar;
    private String firstname;
    private String phone;
    private String role;
}
