package com.example.controller;

import com.example.DTO.DistrictDto;
import com.example.DTO.ProvinceDto;
import com.example.entity.Province;
import com.example.repository.ProvinceRepository;
import com.example.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class DistrictController {

    @Autowired
    private IAddressService addressService;

    @Autowired
    ProvinceRepository provinceRepository;

    @GetMapping("/district")
    public ResponseEntity<List<DistrictDto>> getDistrictByProvince(@RequestParam Long provinceId)
    {
        return new ResponseEntity<>(addressService.getDistrictByProvince(provinceId), HttpStatus.OK);
    }
}
