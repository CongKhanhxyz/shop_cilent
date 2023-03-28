package com.example.controller;

import com.example.DTO.ListProductId;
import com.example.DTO.ProductAdd;
import com.example.DTO.ProductDto;
import com.example.repository.ProductRepository;
import com.example.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    IProductService productService;

    @Autowired
    ProductRepository productRepository;

    @PostMapping()
    public ResponseEntity<?> saveProduct(@RequestBody ProductAdd productAdd) {
        ProductDto productAddSaved = new ProductDto();
        try {
            productAddSaved =  productService.save(productAdd);
        } catch (RuntimeException | IOException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(productAddSaved, HttpStatus.OK);
    }
    @PutMapping("/edit")
    public ResponseEntity<String> updateProduct(@RequestBody ProductAdd productAdd)
    {
        try {
            productService.update(productAdd);
        } catch (RuntimeException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>("Bạn đã cập nhập thành công sản phẩm", HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteProduct(@RequestParam Long productId)
    {
        try {
            productService.delete(productId);
        } catch (RuntimeException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>("Bạn đã xóa thành công sản phẩm", HttpStatus.OK);
    }

    @GetMapping("/{currentPage}")
    public ResponseEntity<List<ProductDto>> getAll(@PathVariable Integer currentPage)
    {
        return new ResponseEntity<>(productService.getAll(currentPage), HttpStatus.OK);
    }
    @GetMapping("/counts")
    public ResponseEntity<Integer> getCount()
    {
        return new ResponseEntity<>(productService.getTotalPage(), HttpStatus.OK);
    }
    @GetMapping()
    public List<ProductDto> getProductByName(@RequestParam String name, @RequestParam int currentPage)
    {
        return productService.getProductByName(name, currentPage);
    }
    @GetMapping("/details")
    public ProductDto getProductById(@RequestParam Long productId)
    {
        return productService.getProductById(productId);
    }

    @GetMapping("/count")
    public ResponseEntity<?> getLastId()
    {
        try {
            productService.getLastIdByProductId();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(productService.getLastIdByProductId(), HttpStatus.OK);
    }
    @GetMapping("/category")
    public ResponseEntity<?> getProductByCategory()
    {
        try {
            productService.getProductByCategory();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(productService.getProductByCategory(), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllProduct()
    {
        try {
            productService.getAllProduct();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
    }
    @GetMapping("/once")
    public ResponseEntity<?> getEachProduct(@RequestParam String listProductIds)
    {
        try {
            productService.getAllByListId(listProductIds);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(productService.getAllByListId(listProductIds), HttpStatus.OK);
    }
}

