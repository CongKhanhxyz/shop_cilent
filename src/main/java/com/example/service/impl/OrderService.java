package com.example.service.impl;

import com.example.DTO.*;
import com.example.entity.*;
import com.example.repository.AddressRepository;
import com.example.repository.CartRepository;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;
import com.example.security.repository.UserRepository;
import com.example.service.IOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void orderProductInCart(OrderRequestDto orderRequestDto) {

        Optional<User> userOptional = userRepository.findById(orderRequestDto.getUserId());
        Optional<Address> optionalAddress = addressRepository.findById(orderRequestDto.getAddressId());
        Arrays.stream(orderRequestDto.getListProductId().split(","))
                .forEach( productId
                        ->
                {
                    CartProductId cartProductId = new CartProductId(orderRequestDto.getUserId(),
                            Long.valueOf(productId));
                    Optional<Cart> cartOptional = cartRepository.findById(cartProductId);
                    if (userOptional.isPresent()) {
                        if (cartOptional.isPresent()) {
                            Order order = new Order();
                            order.setProduct(cartOptional.get().getProduct());
                            order.setUser(userOptional.get());
                            order.setQuantity(cartOptional.get().getQuantity());
                            order.setStatus(1);
                            order.setCreatedDate(LocalDate.now());
                            LocalDate createdDate = LocalDate.now().plusDays(7);
                            order.setShipDate(createdDate);
                            order.setColor(orderRequestDto.getColor());
                            order.setSize(orderRequestDto.getSize());
                            order.setMethodPayment(orderRequestDto.getMethodPayment());
                            order.setAddress(optionalAddress.get());
                            order.setTotalPrice(orderRequestDto.getTotalPrice());
                            orderRepository.save(order);
                        } else {
                            throw new RuntimeException("Không có sản phẩm");
                        }
                    } else {
                        throw new RuntimeException("Không có người dùng");
                    }
                });
    }

    @Override
    public void orderProductNow(OrderRequestDto orderRequestDto) {

    }

    @Override
    public void cancelProduct(Long orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            if (orderOptional.get().getStatus() == 1 || orderOptional.get().getStatus() == 2) {
                orderOptional.get().setStatus(0);
                orderRepository.save(orderOptional.get());
            }
        }
    }
    // 0 là hủy
    // 1 là chờ xác nhận
    // 2 là chờ lấy hàng
    // 3 là đang giao
    // 4 là đẫ giao
    // 2 requestForm Admin

    // update status
    @Override
    public void update(OrderStatusUpdate orderStatusUpdate)
    {
        Optional<Order> orderOptional = orderRepository.findById(orderStatusUpdate.getOrderId());
        if (orderOptional.isPresent())
        {
            orderOptional.get().setStatus(orderStatusUpdate.getStatus());
            orderOptional.get().getProduct().
                    setSoldAmount(orderOptional.get().getProduct().getSoldAmount() + 1);
            Optional<Product> optionalProduct = productRepository.findById(orderOptional.get().getProduct().getProductID());
            if (optionalProduct.isPresent())
            {
                if (optionalProduct.get().getQuantity() >= 1)
                {
                    optionalProduct.get().setQuantity(optionalProduct.get().getQuantity() - 1);
                    productRepository.save(optionalProduct.get());
                }
            }
            orderRepository.save(orderOptional.get());
        }
    }

    @Override
    public OrderDto getDetailOrder(Long orderId) {
        return modelMapper.map(orderRepository.findById(orderId), OrderDto.class);
    }

    @Override
    public Long getLastId() {
        return orderRepository.getLastId();
    }

    @Override
    public List<OrderDto> getAll(Long userId) {
        return orderRepository.getOrderByUserUserId(userId)
                .stream().map(
                        order -> {
                            OrderDto orderDto = modelMapper.map(order, OrderDto.class);
                            orderDto.setProductId(order.getProduct().getProductID());
                            orderDto.setProductName(order.getProduct().getProductName());
                            orderDto.setNameRecived(order.getAddress().getFullnameRecive());
                            orderDto.setPhone(order.getAddress().getPhone());
                            orderDto.setAddress(order.getAddress().getDetailAddress());
                            orderDto.setNewPriceProduct(order.getProduct().getNewPrice());
                            orderDto.setUrlImage(order.getProduct().getPhotosImagePath());
                            return orderDto;
                        }
                ).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getAllProductCompleted(Long userId) {
        return orderRepository.getOrderByUserUserIdAndShipDate(userId, LocalDate.now())
                .stream().map(
                        order -> {
                            OrderDto orderDto = modelMapper.map(order, OrderDto.class);
                            orderDto.setProductId(order.getProduct().getProductID());
                            orderDto.setProductName(order.getProduct().getProductName());
                            orderDto.setNameRecived(order.getAddress().getFullnameRecive());
                            orderDto.setPhone(order.getAddress().getPhone());
                            orderDto.setAddress(order.getAddress().getDetailAddress());
                            orderDto.setNewPriceProduct(order.getProduct().getNewPrice());
                            orderDto.setUrlImage(order.getProduct().getPhotosImagePath());
                            return orderDto;
                        }
                ).collect(Collectors.toList());
    }

    @Override
    public List<ProductReport> getByDate(String startDate, String  endDate) {
        return orderRepository.
                getOrderByCreatedDateLessThanEqualAndCreatedDateGreaterThanEqual(LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .stream().map(
                        order -> modelMapper.map(order, ProductReport.class)
                ).collect(Collectors.toList());
    }
}
