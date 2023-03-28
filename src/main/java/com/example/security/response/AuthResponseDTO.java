package com.example.security.response;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private long id;
    private String accessToken;
    private String tokenType = "Bearer ";
    private String username;
    private String name;
    private String urlImageAvatar;
    private String firstname;
    private String phone;
    private String role;

    public AuthResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    public AuthResponseDTO(long id, String accessToken, String username, String name,
                           String urlImageAvatar, String firstname, String phone, String role){
        this.id = id;
        this.accessToken = accessToken;
        this.username = username;
        this.name = name;
        this.urlImageAvatar = urlImageAvatar;
        this.firstname = firstname;
        this.phone = phone;
        this.role = role;
    }

    public AuthResponseDTO(long id, String accessToken, String username, String name) {
        this.id = id;
        this.accessToken = accessToken;
        this.username = username;
        this.name = name;
    }
}
