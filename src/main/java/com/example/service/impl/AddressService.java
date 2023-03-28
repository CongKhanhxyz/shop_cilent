package com.example.service.impl;

import com.example.DTO.AddressDto;
import com.example.DTO.DistrictDto;
import com.example.DTO.ProvinceDto;
import com.example.entity.*;
import com.example.repository.AddressRepository;
import com.example.repository.DistrictRepository;
import com.example.repository.ProvinceRepository;
import com.example.repository.WardRepository;
import com.example.security.repository.UserRepository;
import com.example.service.IAddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressService implements IAddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    WardRepository wardRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    ProvinceRepository provinceRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<DistrictDto> getDistrictByProvince(Long provinceId) {
        return districtRepository.getDistrictsByProvinceId(provinceId).stream()
                .map(district -> modelMapper.map(district, DistrictDto.class)).
                collect(Collectors.toList());
    }

    @Override
    public List<Ward> getWardByDistrict(Long districtId) {
        return wardRepository.getWardByDistrictId(districtId);
    }

    @Override
    public List<Province> getAllProvince() {
        return provinceRepository.findAll();
    }

    @Override
    public AddressDto saveAddress(AddressDto addressDto) {
        Optional<User> optionalUser = userRepository.findById(addressDto.getUserId());
        Optional<Ward> optionalWard = wardRepository.getWardByName(addressDto.getWardName());
        Address addressSaved = new Address();
        if (optionalUser.isPresent())
        {
            Address address = new Address();
            address.setUser(optionalUser.get());
            address.setDetailAddress(addressDto.getDetailAddress());
//            address.setWard(optionalWard.get());
            address.setTypeAddress(addressDto.getTypeAddress());
            address.setFullnameRecive(addressDto.getFullnameRecive());
            address.setPhone(addressDto.getPhone());
            addressSaved = addressRepository.save(address);
        }
        else {
            throw new RuntimeException("Không thấy người dùng");
        }
        return modelMapper.map(addressSaved, AddressDto.class);
    }

    @Override
    public AddressDto getAddressByAddressId(Long addressId) {
        return modelMapper.map(addressRepository.getById(addressId), AddressDto.class);
    }

    @Override
    public void setDefaultAddress(Long addressId) {
        Optional<Address> optionalAddress = addressRepository.findById(addressId);
        if (optionalAddress.isPresent())
        {
            optionalAddress.get().setDefaultAddress(1);
        }
        else
        {
            throw new RuntimeException("Không thấy địa chỉ");
        }
    }

    @Override
    public AddressDto getAddressByUserIdAndIsDefaultAddress(Long userId) {
        try {
            Address address = addressRepository.getWardIdByUserIdAndIsDefault(userId);
            Ward ward = address.getWard();
            Optional<District> optionalDistrict = districtRepository.findById(ward.getDistrict().getId());
            Optional<Province> optionalProvince = provinceRepository.findById(optionalDistrict.get().getProvince().getId());
            AddressDto addressDto = modelMapper.map(addressRepository.getWardIdByUserIdAndIsDefault(userId),
                    AddressDto.class);
            addressDto.setWardName(ward.getName());
            addressDto.setDistrictName(optionalDistrict.get().getName());
            addressDto.setProvinceName(optionalProvince.get().getName());
            return addressDto;
        } catch (Exception e) {
            throw new RuntimeException("Không có address mặc định");
        }
    }

    @Override
    public AddressDto updateAddress(AddressDto addressDto) {
        Optional<Address> optionalAddress = addressRepository.findById(addressDto.getAddressId());
        Address addressSaved = new Address();
        if (optionalAddress.isPresent()) {
                optionalAddress.get().setDetailAddress(addressDto.getDetailAddress());
                optionalAddress.get().setTypeAddress(addressDto.getTypeAddress());
                optionalAddress.get().setFullnameRecive(addressDto.getFullnameRecive());
                optionalAddress.get().setPhone(addressDto.getPhone());
                optionalAddress.get().setDefaultAddress(addressDto.getDefaultAddress());
                addressSaved = addressRepository.save(optionalAddress.get());
            } else {
                throw new RuntimeException("Không thấy người dùng");
            }
        return modelMapper.map(addressSaved, AddressDto.class);
    }

    @Override
    public List<AddressDto> getAllAddress(Long userId) {
        try {
            return addressRepository.getAllAddressByUserId(userId)
                    .stream().map(
                            address -> modelMapper.map(address, AddressDto.class)
                    ).collect(Collectors.toList());
        }
        catch (Exception e)
        {
            throw new RuntimeException("Không có địa chỉ được lưu");
        }
    }

}
