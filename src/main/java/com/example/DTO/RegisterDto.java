package com.example.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    private Integer id;
    private String username;
    private String password;
    private String fullName;
//    private String tokenType;
//    private String tokenAccess;
}
