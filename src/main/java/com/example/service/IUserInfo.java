package com.example.service;


import com.example.DTO.UserDetailDto;
import com.example.DTO.UserDto;
import com.example.entity.User;

import java.util.Optional;

public interface IUserInfo {
    void saveUser(UserDetailDto userDetailDto);

    UserDetailDto getUserById(Long userId);
    void updateUser(UserDetailDto userDetailDto);
    Long getLastId();
}
