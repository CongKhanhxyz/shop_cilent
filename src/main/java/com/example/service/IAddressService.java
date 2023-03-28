package com.example.service;


import com.example.DTO.AddressDto;
import com.example.DTO.DistrictDto;
import com.example.entity.Province;
import com.example.entity.Ward;

import java.util.List;

public interface IAddressService {
    // lấy tỉnh xong lấy ra huyện
    List<DistrictDto> getDistrictByProvince(Long provinceId);
    List<Ward> getWardByDistrict(Long districtid);

    List<Province> getAllProvince();
    AddressDto saveAddress(AddressDto addressDto);

    AddressDto getAddressByAddressId(Long addressId);

    void setDefaultAddress(Long addressId);
    AddressDto getAddressByUserIdAndIsDefaultAddress(Long userId);

    AddressDto updateAddress(AddressDto addressDto);

    List<AddressDto> getAllAddress(Long userId);


}
