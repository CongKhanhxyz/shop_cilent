package com.example.controller;

import com.example.DTO.OrderRequestDto;
import com.example.DTO.OrderStatusUpdate;
import com.example.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("/api/orders")
@RestController
public class OrderController {

   @Autowired
   OrderService orderService;

//   @PostMapping("/buyInCart")
//   public ResponseEntity<String> orderProductInCart(@RequestParam Long userId,
//                                                    @RequestParam Long productId,
//                                                    @RequestParam Integer quantity)
//   {
//      try {
//         orderService.orderProductInCart(userId, productId, quantity);
//      } catch (Exception e) {
//         return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
//      }
//      return new ResponseEntity<>("Bạn đã mua hàng thành công", HttpStatus.OK);
//   }
   @PostMapping("/buy")
   public ResponseEntity<String> orderProduct(@RequestBody OrderRequestDto orderRequestDto)
   {
      try {
         orderService.orderProductInCart(orderRequestDto);
      } catch (Exception e) {
         return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
      }
      return new ResponseEntity<>("Bạn đã mua hàng thành công", HttpStatus.OK);
   }
   @PutMapping("/cancel")
   public ResponseEntity<String> cancel(@RequestParam Long orderId)
   {
      try {
         orderService.cancelProduct(orderId);
      } catch (Exception e) {
         return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
      }
      return new ResponseEntity<>("Bạn đã hủy đơn thành công", HttpStatus.OK);
   }
   @PutMapping()
   public ResponseEntity<String> update(@RequestBody OrderStatusUpdate orderStatusUpdate)
   {
      orderService.update(orderStatusUpdate);
      return new ResponseEntity<>("Bạn đã cài đặt trạng thái thành công", HttpStatus.OK);
   }
   @GetMapping("/lastId")
   public ResponseEntity<?> getLastId()
   {
      return new ResponseEntity<>(orderService.getLastId(), HttpStatus.OK);
   }
   @GetMapping("/detail")
   public ResponseEntity<?> getDetail(@RequestParam Long orderId)
   {
      return new ResponseEntity<>(orderService.getDetailOrder(orderId), HttpStatus.OK);
   }
   @GetMapping("/all")
   public ResponseEntity<?> getAllOrder(@RequestParam Long userId)
   {
      return new ResponseEntity<>(orderService.getAll(userId), HttpStatus.OK);
   }
   @GetMapping("/report")
   public ResponseEntity<?> getReportProduct(@RequestParam String startDate,
                                             @RequestParam String endDate)
   {
      return new ResponseEntity<>(orderService.getByDate(startDate, endDate), HttpStatus.OK);
   }
}
