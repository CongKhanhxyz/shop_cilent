package com.example.service;


import com.example.DTO.CategoryDto;

import java.util.List;

public interface ICategoryService {
    void save(CategoryDto categoryDto);
    List<CategoryDto> getCategoryName();
}
