package com.example.controller;

import com.example.DTO.CartDeleteAll;
import com.example.DTO.CartRequestDto;
import com.example.DTO.ListProductId;
import com.example.service.impl.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/api/carts")
@RestController
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping()
    ResponseEntity<String> save(@RequestBody CartRequestDto cartRequestDto)
    {
        try {
            cartService.saveProductInCart(cartRequestDto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>("Bạn đã thêm vào giỏ hàng thành công", HttpStatus.OK);
    }
    @DeleteMapping("/all")
    ResponseEntity<String> deleteAll(@RequestBody CartDeleteAll cartDeleteAll)
    {
        try {
            cartService.deleteAllProductInCart(cartDeleteAll);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>("Bạn đã xóa khỏi giỏ hàng", HttpStatus.OK);
    }
    @GetMapping()
    ResponseEntity<?> getAllCart(@RequestParam Long userId)
    {
        try {
            cartService.getAllCartById(userId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(cartService.getAllCartById(userId), HttpStatus.OK);
    }
    @PutMapping()
    ResponseEntity<?> update(@RequestParam Long userId,
                           @RequestParam Long productId,
                           @RequestParam int amount)
    {
        try {
            cartService.changeAmount(userId, productId, amount);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>("Bạn đã cập nhập thành công", HttpStatus.OK);
    }
    @DeleteMapping()
    ResponseEntity<String> delete(@RequestParam Long userId, @RequestParam Long productId)
    {
        try {
            cartService.deleteProductInCart(userId, productId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>("Bạn đã xóa khỏi giỏ hàng", HttpStatus.OK);
    }
    @GetMapping("/each")
    public ResponseEntity<?> getEachProduct(@RequestParam String listProductId,
                                            @RequestParam String userId)
    {
        try {
            cartService.getAllByListId(listProductId, userId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(cartService.getAllByListId(listProductId, userId), HttpStatus.OK);
    }
}
