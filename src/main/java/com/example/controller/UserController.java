package com.example.controller;

import com.example.DTO.ProductAdd;
import com.example.DTO.UserDetailDto;
import com.example.DTO.UserDto;
import com.example.security.repository.UserRepository;
import com.example.service.IUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserInfo userInfo;

    @GetMapping
    public ResponseEntity<UserDetailDto> getUser(@RequestParam Long userId)
    {
        return new ResponseEntity<>(userInfo.getUserById(userId), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> updateUser(@RequestBody UserDetailDto userDetailDto)
    {
        try {
            userInfo.updateUser(userDetailDto);
        } catch (RuntimeException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>("Bạn đã cập nhập thành công thông tin", HttpStatus.OK);
    }
    @GetMapping("/count")
    public ResponseEntity<?> getLastId()
    {
        return new ResponseEntity<>(userInfo.getLastId(), HttpStatus.OK);
    }
}
