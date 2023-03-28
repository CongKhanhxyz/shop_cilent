package com.example.service.impl;

import com.example.DTO.*;
import com.example.entity.Cart;
import com.example.entity.CartProductId;
import com.example.entity.Product;
import com.example.entity.User;
import com.example.repository.CartRepository;
import com.example.repository.ColorRepository;
import com.example.repository.ProductRepository;
import com.example.repository.SizeRepository;
import com.example.security.repository.UserRepository;
import com.example.service.ICartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService implements ICartService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    SizeRepository sizeRepository;

    @Autowired
    ColorRepository colorRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void saveProductInCart(CartRequestDto cartRequestDto) {
        Optional<User> userOptional = userRepository.findById(cartRequestDto.getUserId());
        Optional<Product> productOptional = productRepository.findById(cartRequestDto.getProductId());
        if (userOptional.isPresent())
        {
            if (productOptional.isPresent())
            {
                CartProductId cartProductId = new CartProductId(cartRequestDto.getUserId(),
                        cartRequestDto.getProductId());
                Cart cart = new Cart();
                cart.setCartProductId(cartProductId);
                cart.setProduct(productOptional.get());
                cart.setUserCart(userOptional.get());
                if (productOptional.get().getQuantity() >= cartRequestDto.getQuantity())
                {
                    cart.setQuantity(cartRequestDto.getQuantity());
                    cart.setCreatedTime(LocalDateTime.now());
//                cart.setColor(cartRequestDto.getColor());
//                cart.setSize(cartRequestDto.getSize());
                    cartRepository.save(cart);
                }
                else {
                    throw new RuntimeException("Không đủ số lượng");
                }
            }
            else {
                throw new RuntimeException("Không có sản phẩm");
            }
        }
        else {
            throw new RuntimeException("Không có người dùng");
        }
    }

    @Override
    public void deleteAllProductInCart(CartDeleteAll cartDeleteAll) {
        Optional<User> userOptional = userRepository.findById(cartDeleteAll.getUserId());
        cartDeleteAll.getListProductId().stream().forEach(
                productId -> {
                    Optional<Product> productOptional = productRepository.findById(productId);
                    if (userOptional.isPresent())
                    {
                        if (productOptional.isPresent())
                        {
                            CartProductId cartProductId = new CartProductId(cartDeleteAll.getUserId(),
                                    productId);
                            cartRepository.deleteById(cartProductId);
                        }
                        else {
                            throw new RuntimeException("Không có sản phẩm");
                        }
                    }
                    else {
                        throw new RuntimeException("Không có người dùng");
                    }
                }
        );
    }

    @Override
    public void deleteProductInCart(Long userId, Long productId) {
        Optional<User> userOptional = userRepository.findById(userId);
                    Optional<Product> productOptional = productRepository.findById(productId);
                    if (userOptional.isPresent())
                    {
                        if (productOptional.isPresent())
                        {
                            CartProductId cartProductId = new CartProductId(userId,
                                    productId);
                            cartRepository.deleteById(cartProductId);
                        }
                        else {
                            throw new RuntimeException("Không có sản phẩm");
                        }
                    }
                    else {
                        throw new RuntimeException("Không có người dùng");
                    }
    }

    @Override
    public List<CartDto> getAllCartById(Long userId) {
        return cartRepository.getAllCartByUserId(userId)
                .stream().map(cart ->
                        {
                            CartDto cartDto =
                                    modelMapper.map(cart, CartDto.class);
                            cartDto.setTotal(cartRepository.findAll().size());
                            cartDto.setUrlImage(cart.getProduct().getPhotosImagePath());
                            cartDto.setNewPrice(cart.getProduct().getNewPrice());
                            cartDto.setProductName(cart.getProduct().getProductName());
                            return cartDto;
                        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void changeAmount(Long userId, Long productId, int amount) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        CartProductId cartProductId = new CartProductId(userId, productId);
        Optional<Cart> optionalCart = cartRepository.findById(cartProductId);
        if (optionalProduct.isPresent())
        {
            int amountTotal = optionalCart.get().getQuantity() + amount;
            if (amountTotal <= optionalProduct.get().getQuantity())
            {
                cartRepository.updateQuantity(userId, productId, amount);
            }
            else {
                throw new RuntimeException("sản phẩm đã hết");
            }
        }
    }

    @Override
    public List<CartDto> getAllByListId(String listProductId, String userId) {
        return Arrays.stream(listProductId.split(",")).map(
                productId ->
                {
                    CartProductId cartProductId = new CartProductId(Long.valueOf(userId),
                            Long.valueOf(productId));
                    Cart cart = cartRepository.findById(cartProductId).get();
                    CartDto cartDto =  modelMapper.map(cartRepository.findById(cartProductId).get(), CartDto.class);
                    cartDto.setUrlImage(cart.getProduct().getPhotosImagePath());
                    cartDto.setNewPrice(cart.getProduct().getNewPrice());
//                    cartDto.setCategoryName(cart.getProduct().getCategory().getCategoryName());
                    cartDto.setTotal(cartRepository.findAll().size());

                    return cartDto;
                }
        ).collect(Collectors.toList());

    }
}

