package com.example.service.impl;

import com.example.DTO.CategoryDto;
import com.example.entity.Category;
import com.example.repository.CategoryRepository;
import com.example.service.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void save(CategoryDto categoryDto) {
        if (categoryRepository.findByCategoryName(categoryDto.getCategoryName()).isPresent())
        {
            throw new RuntimeException("Loại hàng này đã tồn tại");
        }
        else {
            categoryRepository.save(modelMapper.map(categoryDto, Category.class));
        }
    }

    @Override
    public List<CategoryDto> getCategoryName() {
        return categoryRepository.findAll().stream().map(
                category -> modelMapper.map(category, CategoryDto.class)
        ).collect(Collectors.toList());
    }
}
