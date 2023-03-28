package com.example.controller;

import com.example.DTO.AddressDto;
import com.example.entity.Province;
import com.example.entity.Ward;
import com.example.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private IAddressService addressService;

    @GetMapping("/province")
    public ResponseEntity<List<Province>> getAllProvince()
    {
        return new ResponseEntity<>(addressService.getAllProvince(), HttpStatus.OK);
    }

    @GetMapping("/ward")
    public ResponseEntity<List<Ward>> getWardByDistrict(@RequestParam Long districtId)
    {
        return new ResponseEntity<>(addressService.getWardByDistrict(districtId), HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<AddressDto> saveAddress(@RequestBody AddressDto addressDto)
    {
        return new ResponseEntity<>(addressService.saveAddress(addressDto), HttpStatus.OK);
    }
    @PutMapping()
    public ResponseEntity<AddressDto> updateAddress(@RequestBody AddressDto addressDto)
    {
        return new ResponseEntity<>(addressService.updateAddress(addressDto), HttpStatus.OK);
    }
//    @PutMapping("/default")
//    public ResponseEntity<String> saveDefault(@RequestBody Long addressId)
//    {
//      try {
//          addressService.setDefaultAddress(addressId);
//      } catch (Exception e)
//      {
//          return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
//      }
//        return new ResponseEntity<>("Đã đặt địa chỉ làm mặc định", HttpStatus.OK);
//    }
    @GetMapping("/detail")
    public ResponseEntity<?> getDetailAddress(@RequestParam Long addressId)
    {
        try
        {
            addressService.getAddressByAddressId(addressId);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new AddressDto(), HttpStatus.OK);
        }
        return new ResponseEntity<>(addressService.getAddressByAddressId(addressId), HttpStatus.OK);
    }
    @GetMapping("/default")
    public ResponseEntity<?> getDefaultAddress(@RequestParam Long userId)
    {
        try
        {
            addressService.getAddressByUserIdAndIsDefaultAddress(userId);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new AddressDto(), HttpStatus.OK);
        }
        return new ResponseEntity<>(addressService.getAddressByUserIdAndIsDefaultAddress(userId), HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<?> getAllAddress(@RequestParam Long userId)
    {
        List<AddressDto> allAddress  = new ArrayList<>();
        try
        {
             allAddress = addressService.getAllAddress(userId);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(allAddress, HttpStatus.OK);
        }
        return new ResponseEntity<>(addressService.getAllAddress(userId), HttpStatus.OK);
    }
}
