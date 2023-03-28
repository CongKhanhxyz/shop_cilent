package com.example.service;


import com.example.DTO.*;

import java.util.List;

public interface ICartService {
    void saveProductInCart(CartRequestDto cartRequestDto);

    void deleteAllProductInCart(CartDeleteAll cartDeleteAll);
    void deleteProductInCart(Long userId, Long productId);
    List<CartDto> getAllCartById(Long userId);
    void changeAmount(Long userId, Long productId, int amount);
    List<CartDto> getAllByListId(String listProductId, String userId);
}
