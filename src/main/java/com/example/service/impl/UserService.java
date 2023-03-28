package com.example.service.impl;

import com.example.DTO.UserDetailDto;
import com.example.DTO.UserDto;
import com.example.entity.Address;
import com.example.entity.User;
import com.example.entity.Ward;
import com.example.repository.AddressRepository;
import com.example.repository.WardRepository;
import com.example.security.repository.UserRepository;
import com.example.service.IUserInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserInfo {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    WardRepository wardRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void saveUser(UserDetailDto userDetailDto) {
        Optional<User> optionalUser = userRepository.findById(userDetailDto.getUserId());
        if (optionalUser.isPresent())
        {
            optionalUser.get().setFirstname(userDetailDto.getFirstname());
            optionalUser.get().setPhone(userDetailDto.getPhone());
            optionalUser.get().setUrlImageAvatar(userDetailDto.getUrlImageAvatar());
            userRepository.save(optionalUser.get());
        }
        else {
            throw new RuntimeException("Không có tài khoản");
        }
    }

    @Override
    public UserDetailDto getUserById(Long userId) {
        User user = userRepository.findById(userId).get();
        UserDetailDto userDetailDto
                = modelMapper.map(user, UserDetailDto.class);
        userDetailDto.setUrlImageAvatar(user.getPhotosImagePath());
        return userDetailDto;
    }

    @Override
    public void updateUser(UserDetailDto userDetailDto) {
        Optional<User> optionalUser = userRepository.findById(userDetailDto.getUserId());
        if (optionalUser.isPresent())
        {
            optionalUser.get().setLastname(userDetailDto.getLastname());
            optionalUser.get().setFirstname(userDetailDto.getFirstname());
            optionalUser.get().setPhone(userDetailDto.getPhone());
            optionalUser.get().setUrlImageAvatar(userDetailDto.getUrlImageAvatar());
            Address address = new Address();
            Optional<Ward> wardByName = wardRepository.getWardByName(userDetailDto.getWardName());
            address.setWard(wardByName.get());
//            addressRepository.f
            address.setUser(optionalUser.get());
            address.setDetailAddress(userDetailDto.getAddress());
            userRepository.save(optionalUser.get());
            addressRepository.save(address);
        }
        else {
            throw new RuntimeException("Không có tài khoản");
        }
    }

    @Override
    public Long getLastId() {
        try {
            userRepository.getLastId();
        }
        catch (Exception e)
        {
            throw new RuntimeException("Không có last id");
        }
        return userRepository.getLastId();
    }
}
