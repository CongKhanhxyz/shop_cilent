package com.example.service.impl;

import com.example.DTO.DiscountDto;
import com.example.DTO.DiscountProductDto;
import com.example.DTO.ListProductId;
import com.example.DTO.OrderDto;
import com.example.entity.Discount;
import com.example.entity.DiscountProduct;
import com.example.entity.DiscountProductId;
import com.example.entity.Product;
import com.example.repository.DiscountProductRepository;
import com.example.repository.DiscountRepository;
import com.example.repository.ProductRepository;
import com.example.service.IDiscountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DiscountService implements IDiscountService {

    @Autowired
    DiscountRepository discountRepository;

    @Autowired
    DiscountProductRepository discountProductRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public DiscountDto addDiscount(DiscountProductDto discountProductDto) {
        Discount discount = new Discount();
        discount.setName(discountProductDto.getName());
        discount.setUnit(discountProductDto.getUnit());
        discount.setMethod(discountProductDto.getMethod());
        discount.setValue(discountProductDto.getValue());
        discount.setStartDate(LocalDate.parse(discountProductDto.getStartDate()));
        discount.setEndDate(LocalDate.parse(discountProductDto.getEndDate()));
        List<Long> listProductId = new ArrayList<>();
                discountProductDto.getCategory().stream()
                .forEach(
                        category ->
                    productRepository.getProductsByCategory_CategoryName(category).stream()
                            .forEach(product -> {
                                listProductId.add(product.getProductID());
                            })
                );
                discountProductDto.getProducts().forEach(
                        product -> listProductId.add(Long.valueOf(product))
                );

//        discount.setStartDate(LocalDate.parse(discountDto.getStartDate()));
//        discount.setEndDate(LocalDate.parse(discountDto.getEndDate()));
        // 1 discount có 1 list sản phẩm
        // 1 sản phẩm có
        Discount discountSaved  = discountRepository.save(discount);
        if (!listProductId.isEmpty()) {
            listProductId.stream().forEach(
                    productId ->
                    {
                        DiscountProduct discountProduct = new DiscountProduct();
                        Optional<Product> optionalProduct = productRepository.findById(productId);
                        if (optionalProduct.isEmpty()) {
                            throw new RuntimeException("Không có sản phẩm ");
                        } else {
                            DiscountProductId discountProductId =
                                    new DiscountProductId(discountSaved.getDiscountId(),
                                    optionalProduct.get().getProductID());
                            discountProduct.setDiscountProductId(discountProductId);
                            discountProduct.setDiscount(discountSaved);
                            discountProduct.setProduct(optionalProduct.get());
                            discountProductRepository.save(discountProduct);
                        }
                    }
            );
        }
        return modelMapper.map(discount, DiscountDto.class);
    }

    @Override
    public void deleteDiscount(Long discountId) {
        Optional<Discount> optionalDiscount = discountRepository.findById(discountId);
        if (optionalDiscount.isEmpty())
        {
            throw new RuntimeException("Không có khuyến mãi ");
        }
        else {
            discountRepository.deleteById(discountId);
        }
    }

    @Transactional
    @Override
    public DiscountDto update(DiscountProductDto discountProductDto) {
        Optional<Discount> discountOptional = discountRepository.findById(discountProductDto.getDiscountId());
        if (discountOptional.isEmpty()) {
            throw new RuntimeException("Không có khuyến mại đang tìm");
        }
        discountOptional.get().setName(discountProductDto.getName());
        discountOptional.get().setUnit(discountProductDto.getUnit());
        discountOptional.get().setMethod(discountProductDto.getMethod());
        discountOptional.get().setValue(discountProductDto.getValue());

        return modelMapper.map(discountRepository.save( discountOptional.get()), DiscountDto.class);
    }

    @Override
    public List<DiscountDto> getDiscount() {
        return discountRepository.findAll().stream().map(
                discount -> modelMapper.map(discount, DiscountDto.class)
        ).collect(Collectors.toList());
    }

    @Override
    public List<DiscountDto> getDiscountByProductId(String listProductId) {
        List<DiscountDto> discountDtos = new ArrayList<>();
        List<Long> listDicountId= new ArrayList<>();

        Arrays.stream(listProductId.split(",")).forEach(
                productId ->
                {
                    discountProductRepository.findDiscountProductByProductProductID(
                            Long.parseLong(productId))
                            .stream().forEach(
                                    discountProduct -> listDicountId.add(
                                            discountProduct.getDiscount().getDiscountId())
                            );
                }
        );
        return listDicountId.stream().map(
                discountId ->
                {
                    DiscountDto discountDto =
                            modelMapper.map(discountRepository.getReferenceById(discountId),
                                    DiscountDto.class);
                    List<Long> listProductIdEach = new ArrayList<>();
                    discountProductRepository.
                            findDiscountProductByDiscountDiscountIdD(discountId)
                            .stream().forEach(
                                    discountProduct -> listProductIdEach.add(discountProduct.getProduct().getProductID())
                            );
                    discountDto.setListProductId(listProductIdEach);
                    return discountDto;
                }
        ).collect(Collectors.toList());
    }

    @Override
    public DiscountDto getDiscountById(Long discountId) {
        Optional<Discount> optionalDiscount = discountRepository.findById(discountId);
        DiscountDto discountDto = modelMapper
                .map(optionalDiscount.get(), DiscountDto.class);
        discountDto.setStartDate(String.valueOf(optionalDiscount.get().getStartDate()));
        discountDto.setEndDate(String.valueOf(optionalDiscount.get().getEndDate()));
        return discountDto;
    }
}
