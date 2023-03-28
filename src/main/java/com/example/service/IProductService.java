package com.example.service;


import com.example.DTO.ListProductId;
import com.example.DTO.ProductAdd;
import com.example.DTO.ProductByCategory;
import com.example.DTO.ProductDto;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IProductService {
    public Integer getTotalPage();
    public ProductDto save(ProductAdd productAdd) throws IOException;
    List<ProductDto> getAll(int currentPage);
    void delete(Long productId);
    public ProductDto update(ProductAdd productAdd);
    List<ProductDto> getProductByName(String name, int currentPage);
    ProductDto getProductById(Long productId);

    Long getLastIdByProductId();

    List<ProductByCategory> getProductByCategory();

    List<ProductDto> getAllProduct();

    List<ProductDto> getAllByListId(String listProductIds);
}
