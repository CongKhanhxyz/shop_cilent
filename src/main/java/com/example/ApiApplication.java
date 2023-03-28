package com.example;

import com.example.DTO.OrderStatusUpdate;
import com.example.entity.Category;
import com.example.entity.Order;
import com.example.repository.CategoryRepository;
import com.example.repository.DiscountProductRepository;
import com.example.repository.OrderRepository;
import com.example.repository.ProvinceRepository;
import com.example.service.IOrderService;
import com.example.service.impl.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class ApiApplication implements CommandLineRunner  {

	@Autowired
	CategoryRepository categoryRepository;
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Autowired
	DiscountProductRepository discountProductRepository;
//
//	@Autowired
//	IOrderService orderService;
//
//	@Autowired
//	OrderRepository orderRepository;

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

	@Override
	public void run(String... args) throws Exception {
//		discountProductRepository.findDiscountProductByDiscountDiscountIdD(1L).stream()
//				.forEach(discountProduct ->
//						System.out.println(discountProduct.getProduct().getProductName()));
//		System.out.println("khanh");
//		List<Order> orders = orderRepository.getOrderByShipDateGreaterThan(LocalDate.now());
//		if (!orders.isEmpty())
//		{
//			orders.stream().forEach(
//					order -> {
//						OrderStatusUpdate orderStatusUpdate = new
//								OrderStatusUpdate();
//						orderStatusUpdate.setStatus(4);
//						order.setOrderId(order.getOrderId());
//						orderService.update(orderStatusUpdate);
//					}
//			);
//		}
//		OrderStatusUpdate orderStatusUpdate = new
//				OrderStatusUpdate();
//		orderStatusUpdate.setStatus(4);
//		orderService.update(o);
	}
}