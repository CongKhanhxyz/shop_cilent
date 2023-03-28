package com.example.controller;

import com.example.DTO.CategoryDto;
import com.example.service.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RequestMapping(value = "/api/categories")
@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping()
    public ResponseEntity<String> save(@RequestBody CategoryDto categoryDto)
    {
        try {
            categoryService.save(categoryDto);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>("Bạn đã lưu loại sản phẩm thành công", HttpStatus.OK);
    }
    @GetMapping()
    public List<CategoryDto> get()
    {
        return categoryService.getCategoryName();
    }
}
