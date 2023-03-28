package com.example.shopfe.model;

import lombok.Data;

@Data
public class userDTO {
    private int id;
    private String username;
    private String password;
    private String tokenType;
    private String tokenAccess;
}
