package com.example.service;


import com.example.DTO.OrderDto;
import com.example.DTO.OrderRequestDto;
import com.example.DTO.OrderStatusUpdate;
import com.example.DTO.ProductReport;

import java.util.List;

public interface IOrderService {
    void orderProductInCart(OrderRequestDto orderRequestDto);

    void orderProductNow(OrderRequestDto orderRequestDto);


    void cancelProduct(Long orderId);

    void update(OrderStatusUpdate orderStatusUpdate);

    OrderDto getDetailOrder(Long orderId);
    Long getLastId();
    List<OrderDto> getAll(Long userId);

    List<OrderDto> getAllProductCompleted(Long userId);

    List<ProductReport> getByDate(String startDate, String endDate);
}
