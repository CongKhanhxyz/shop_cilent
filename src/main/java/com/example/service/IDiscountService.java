package com.example.service;

import com.example.DTO.DiscountDto;
import com.example.DTO.DiscountProductDto;
import com.example.DTO.ListProductId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDiscountService {

    public DiscountDto addDiscount(DiscountProductDto discountDto);
    public void deleteDiscount(Long discountId);
    public DiscountDto update(DiscountProductDto discountDto);
    public List<DiscountDto> getDiscount();
    public List<DiscountDto> getDiscountByProductId(String listProductId);
    public DiscountDto getDiscountById(Long discountId);

}
