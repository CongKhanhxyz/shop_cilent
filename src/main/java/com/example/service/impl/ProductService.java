package com.example.service.impl;

import com.example.DTO.*;
import com.example.entity.Category;
import com.example.entity.Color;
import com.example.entity.Product;
import com.example.entity.Size;
import com.example.repository.CategoryRepository;
import com.example.repository.ColorRepository;
import com.example.repository.ProductRepository;
import com.example.repository.SizeRepository;
import com.example.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponentModule;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    private static final Integer size = 4;

    private final Path storageFolder = Paths.get("uploads");

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SizeRepository sizeRepository;

    @Autowired
    ColorRepository colorRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JsonComponentModule json;

    public Integer getTotalPage()
    {
      return Math.toIntExact(productRepository.count());
    }

    public ProductService() {
        try {
            Files.createDirectories(storageFolder);
        }catch (IOException exception) {
            throw new RuntimeException("Cannot initialize storage", exception);
        }
    }
    @Override
    public ProductDto save(ProductAdd productAdd) throws IOException {
        Product product = new Product();
        product.setProductID(productAdd.getProductId());
        product.setProductName(productAdd.getProductName());
        product.setOldPrice(productAdd.getOldPrice());
        product.setQuantity(productAdd.getQuantity());
        product.setPercentDiscount(productAdd.getPercentDiscount());
        product.setNewPrice(productAdd.getNewPrice());
        product.setSoldAmount(productAdd.getSoldAmount());
        product.setTotalLike(productAdd.getTotalLike());
        product.setStatus(productAdd.getStatus());
        product.setShortDescription(productAdd.getShortDescription());
        product.setUrlImage(productAdd.getUrlImage());
        Optional<Category> optionalCategory =
                categoryRepository.findByCategoryName(productAdd.getCategoryName());
        if (optionalCategory.isPresent())
        {
            product.setCategory(optionalCategory.get());
        }
        Product productSaved = productRepository.save(product);

//        if (!productAdd.getSizes().isEmpty()) {
//            productAdd.getSizes().stream().map(
//                    sizeObj -> {
//                        System.out.println(sizeObj);
//                        Size sizeToSave = new Size();
//                        sizeToSave.setProduct(product);
//                        sizeToSave.setName(sizeObj.getName());
//                        Size sizeSaved = sizeRepository.save(sizeToSave);
//                        sizeObj.getColors().stream().map(
//                                colorDto -> {
//                                    Color color = new Color();
//                                    color.setSize(sizeSaved);
//                                    color.setQuantity(colorDto.getQuantity());
//                                    color.setName(colorDto.getName());
//                                    colorRepository.save(color);
//                                    return modelMapper.map(color, ColorDto.class);
//                                }
//                        ).collect(Collectors.toList());
//
//                        sizeSaved.setQuantity(sizeObj.getQuantity());
//                        return sizeSaved;
//                    }
//            ).collect(Collectors.toList());
//        }
        return modelMapper.map(productSaved, ProductDto.class);
    }



    @Override
    public List<ProductDto> getAll(int currentPage) {
        Pageable pageable = PageRequest.of(currentPage, size);
        return productRepository.findAll(pageable)
                .stream().map(product ->
//                        modelMapper.map(product, ProductDto.class)
                        {
                            ProductDto productDto = modelMapper.map(product, ProductDto.class);
                            productDto.setUrlImage(product.getPhotosImagePath());
                            return productDto;
                        }
                )
                .collect(Collectors.toList());

    }

    @Override
    public void delete(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public ProductDto update(ProductAdd productAdd) {

        Optional<Product> optionalProduct = productRepository.findById(productAdd.getProductId());
        if (optionalProduct.isEmpty())
        {
            throw new RuntimeException("Không có sản phẩm ");
        }
        optionalProduct.get().setProductName(productAdd.getProductName());
        if (productAdd.getUrlImage() != null)
        {
            optionalProduct.get().setUrlImage(productAdd.getUrlImage());
        }
        optionalProduct.get().setOldPrice(productAdd.getOldPrice());
        Optional<Category> optionalCategory =
                categoryRepository.findByCategoryName(productAdd.getCategoryName());
        if (optionalCategory.isPresent())
        {
            optionalProduct.get().setCategory(optionalCategory.get());
        }
        optionalProduct.get().setQuantity(productAdd.getQuantity());
        optionalProduct.get().setShortDescription(productAdd.getShortDescription());
        return modelMapper.map(productRepository.save(optionalProduct.get()), ProductDto.class);
    }

    @Override
    public List<ProductDto> getProductByName(String name, int currentPage) {
        Pageable pageable = PageRequest.of(currentPage, size);
        return productRepository.findProductByProductNameContaining(name, pageable)
                .stream().map(
                        product -> modelMapper.map(product, ProductDto.class)
                ).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long productId) {
        Product product = productRepository.getReferenceById(productId);
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        productDto.setUrlImage(product.getPhotosImagePath());
        productDto.setShortDescription(product.getShortDescription());
        if (product.getTotalLike() > 0 && product.getSoldAmount() > 0)
        {
            float starAmount = product.getTotalLike() / product.getSoldAmount();
            float startCount = Float.parseFloat(Float.toString(starAmount).substring(0, 1));
            float isHaftStar = Float.parseFloat(Float.toString(starAmount).substring(2, 3));
            int isHalfInt = Integer.parseInt(Float.toString(starAmount).substring(2, 3));
            productDto.setStarCountTail(isHalfInt);
            if (isHaftStar > 5f)
            {
                productDto.setIsHalfCount(1);
            }
            productDto.setStarCount(Integer.parseInt(Float.toString(starAmount).substring(0, 1)));
        }
//            productDt
//        }
        productDto.setPercentDiscount(product.getPercentDiscount());
        productDto.setSoldAmount(product.getSoldAmount());
        productDto.setReviewAmount(product.getReviewAmount());
        Optional<List<Size>> optionalSize = sizeRepository.getSizeByProduct(product.getProductID());
        List<SizeDto> sizeDtos = new ArrayList<>();
        if (optionalSize.isPresent())
        {
            sizeDtos = optionalSize.get().stream()
                    .map(
                            sizeFind -> {
                                SizeDto sizeDto = new SizeDto();
                                sizeDto.setName(sizeFind.getName());
                                sizeDto.setQuantity(sizeFind.getQuantity());
                                Optional<List<Color>> optionalColorList = colorRepository.getColorBySize(sizeFind.getId());
                                if (optionalColorList.isPresent())
                                {
                                   sizeDto.setColors(optionalColorList.get().stream().map(
                                           color -> {
                                               ColorDto colorDto = new ColorDto();
                                               colorDto.setName(color.getName());
                                               colorDto.setQuantity(color.getQuantity());
                                               return colorDto;
                                           }
                                   ).collect(Collectors.toList()));
                                }
                                return sizeDto;
                            }
                    ).collect(Collectors.toList());
        }
        productDto.setSizes(sizeDtos);
        return productDto;
    }

    @Override
    public Long getLastIdByProductId() {
        Long lastId = productRepository.getLastId();
        if(lastId != null) {
            return lastId;
        } else {
            return 1L;
        }
    }

    public List<ProductByCategory> getProductByCategory() {
        List<ProductByCategory> productByCategorys = new ArrayList<>();
        categoryRepository.findAll()
                .stream().forEach(
                        category ->
                        {
                            List<Product> products = productRepository.
                                    getProductsByCategoryName(category.getCategoryName());
                            ProductByCategory productByCategory =
                                    new ProductByCategory();
                            productByCategory.setCategoryId(category.getCategoryID());
                            productByCategory.setCategoryName(category.getCategoryName());
                            productByCategory.setSize(products.size());
                            List<Long> listProductId = new ArrayList<>();
                            products.stream().forEach(
                                    product ->
                                        listProductId.add(product.getProductID())
                            );
                            productByCategory.setListProductId(listProductId);
                            productByCategorys.add(productByCategory);
                        }
                );
        return productByCategorys;
    }

    @Override
    public List<ProductDto> getAllProduct() {
        return productRepository.findAll().stream()
                .map(product ->
                {
                   ProductDto productDto = modelMapper.map(product, ProductDto.class);
                   productDto.setUrlImage(product.getPhotosImagePath());
                   return productDto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getAllByListId(String listProductIds) {
        return Arrays.stream(listProductIds.split(",")).map(
                productId ->
                    modelMapper.
                            map(productRepository.findById(Long.parseLong(productId)).get(),
                                    ProductDto.class)

        ).collect(Collectors.toList());
    }
}
