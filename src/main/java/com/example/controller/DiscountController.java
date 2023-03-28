package com.example.controller;

import com.example.DTO.DiscountDto;
import com.example.DTO.DiscountProductDto;
import com.example.DTO.ListProductId;
import com.example.repository.DiscountRepository;
import com.example.service.IDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/discounts")
public class DiscountController {

    @Autowired
    IDiscountService discountService;

    @Autowired
    DiscountRepository discountRepository;
//    @GetMapping("/all")
//    ResponseEntity<List<DiscountDto>> getDiscount()
//    {
//        List<DiscountDto> discountDtoList;
//        try {
//            discountDtoList = discountService.getDiscount();
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
//        }
//        return new ResponseEntity<>(discountDtoList, HttpStatus.OK);
//    }
    @GetMapping("/products")
    ResponseEntity<List<DiscountDto>> getDiscountByProductId(@RequestParam String listProductId)
    {
        List<DiscountDto> discountDtoList;
        try {
            discountDtoList = discountService.getDiscountByProductId(listProductId);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(discountDtoList, HttpStatus.OK);
    }
//    @PostMapping()
//    ResponseEntity<List<DiscountDto>> getDiscount()
//    {
//        List<DiscountDto> discountDtoList;
//        try {
//            discountDtoList = discountService.getDiscount();
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
//        }
//        return new ResponseEntity<>(discountDtoList, HttpStatus.OK);
//    }
    @PostMapping("/save")
    ResponseEntity<?> saveDiscount(@RequestBody DiscountProductDto discountProductDto)
    {
        DiscountDto discountDtoSaved = new DiscountDto();
        try {
            discountService.addDiscount(discountProductDto);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(discountDtoSaved, HttpStatus.OK);
    }
    @PutMapping
    ResponseEntity<?> updateDiscount(@RequestBody DiscountProductDto discountProductDto)
    {
        DiscountDto discountDtoSaved = new DiscountDto();
        try {
            discountService.update(discountProductDto);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(discountDtoSaved, HttpStatus.OK);
    }
    @DeleteMapping
    ResponseEntity<?> deleteDiscount(@RequestParam Long discountId)
    {
        DiscountDto discountDtoSaved = new DiscountDto();
        try {
            discountService.deleteDiscount(discountId);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>("Bạn đã xóa thành công discount", HttpStatus.OK);
    }
    @GetMapping("/detail")
    ResponseEntity<?> getDiscountById(@RequestParam Long discountId)
    {
        return new ResponseEntity<>(discountService.getDiscountById(discountId), HttpStatus.OK);
    }
    @GetMapping("/lastId")
    ResponseEntity<?> getDiscountById()
    {
        return new ResponseEntity<>(discountRepository.getLastId(), HttpStatus.OK);
    }
}
