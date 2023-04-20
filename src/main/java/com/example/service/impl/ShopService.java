package com.example.service.impl;

import com.example.repository.ShopRepository;
import com.example.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService implements IShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Override
    public void followShop(Long shopId) {
        
    }
}
