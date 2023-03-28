package com.example.shopfe.controllers;

import com.example.shopfe.constants.Api;
import com.example.shopfe.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private RestTemplate restTemplate;

    private static String apiGetCategory = Api.baseURL + "/api/categories";

    private static String apiAddProduct = Api.baseURL + "/api/products";

    private static String apiGetTotalPage = Api.baseURL + "/api/products/counts";

    private static String apiGetDetailProduct = Api.baseURL + "/api/products/details";

    private static String apiGetReviews = Api.baseURL + "/api/reviews";

    private static String apiGetStar = Api.baseURL + "/api/reviews/star";

    private static String apiGetLastId = Api.baseURL + "/api/products/count";

    private static String apiEditProduct = Api.baseURL + "/api/products/edit";


    public String removeComma(String text) {
        String regex = "(?<=[\\d])(,)(?=[\\d])";
        Pattern p = Pattern.compile(regex);
        String str = text;
        Matcher m = p.matcher(str);
        str = m.replaceAll("");
        return str;
    }

    @GetMapping()
    public String viewAddProduct(Model model)
    {
        ResponseEntity<CategoryDto[]> response = restTemplate.getForEntity(apiGetCategory, CategoryDto[].class);
        CategoryDto[] categoryDtos = response.getBody();
        model.addAttribute("categoryList", categoryDtos);
        return "/productAdd";
    }
    @RequestMapping("/create")
    public String addProduct(Model model, @RequestParam String productName,
            @RequestParam String oldPrice, @RequestParam String percentDiscount,
            @RequestParam int quantity, @RequestParam String shortDescription,
            @RequestParam String newPrice,
            @RequestParam String categoryName, @RequestParam("urlImage") MultipartFile multipartFile,
                             HttpServletRequest request
          ) throws IOException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpSession session = request.getSession();
        JwtResponseDTO jwtResponseDTO = (JwtResponseDTO)session.getAttribute("jwtResponse");
        httpHeaders.set(HttpHeaders.AUTHORIZATION,"Bearer "+jwtResponseDTO.getAccessToken());
        ResponseEntity<Long> responseLastId = restTemplate.getForEntity(apiGetLastId,Long.class);
        System.out.println(responseLastId.getBody());
        Long lastId = responseLastId.getBody();
        String  productId = "product"+(lastId + 1);
        String imagePath = null;
        Path des = FileSystems.getDefault().getPath("imgs/" + productId);
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        if(!fileName.isEmpty()) {
            if (!Files.exists(des)) {
                Files.createDirectories(des);
            }
            try (InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = des.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ioe) { }
            imagePath = fileName;
        }
        Product product = new Product();
        product.setProductName(productName);
        product.setShortDescription(shortDescription);
        product.setOldPrice(Double.parseDouble(removeComma(oldPrice)));
        product.setNewPrice(Double.parseDouble(removeComma(newPrice)));
        product.setStatus(1);
        product.setPercentDiscount(Integer.parseInt(percentDiscount));
        product.setUrlImage(imagePath);
        product.setQuantity(quantity);
        product.setCategoryName(categoryName);
        System.out.println(product);
        HttpEntity<Product> httpEntity = new HttpEntity<>(product, httpHeaders);
        ResponseEntity<String> response
                = restTemplate.exchange(apiAddProduct, HttpMethod.POST, httpEntity, String.class);
        return "redirect:/";
    }
    @GetMapping("/detail")
    public String getDetailProduct(@RequestParam Long productId, Model model)
    {

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(apiGetDetailProduct)
                .queryParam("productId", "{productId}")
                .encode()
                .toUriString();
        Map<String, Long> params = new HashMap<>();
        params.put("productId", productId);
        ResponseEntity<ProductDetailDto> response =
                restTemplate.getForEntity(urlTemplate,ProductDetailDto.class,params);
        ProductDetailDto product = response.getBody();
        String urlTemplateReviews = UriComponentsBuilder.fromHttpUrl(apiGetReviews)
                .queryParam("productId", productId)
            .queryParam("currentPage", "{currentPage}")
            .encode()
            .toUriString();
        Map<String, Integer> paramForReview = new HashMap<>();
        paramForReview.put("currentPage", 0);
        paramForReview.put("productId", productId.intValue());
        ResponseEntity<ReviewDto[]> responseReview =
                restTemplate.getForEntity(urlTemplateReviews,ReviewDto[].class,paramForReview);
        ReviewDto[] reviews = responseReview.getBody();

        // ListStar
        String urlTemplateStar = UriComponentsBuilder.fromHttpUrl(apiGetStar)
                .queryParam("productId", productId)
                .encode()
                .toUriString();
        Map<String, Integer> paramForStar = new HashMap<>();
        paramForStar.put("productId", productId.intValue());
        ResponseEntity<Integer[]> responseStar =
                restTemplate.getForEntity(urlTemplateStar,Integer[].class,paramForStar);
        Integer[] countOfRanks = responseStar.getBody();
        int size = countOfRanks.length;
        for (int i = 0; i < size; i++)
        {
            model.addAttribute("count" + i ,countOfRanks[i]);
        }
        model.addAttribute("product",product);
        model.addAttribute("reviewSize", reviews.length);
        model.addAttribute("reviews",reviews);
        model.addAttribute("user",new User());
        return "productDetail";
    }
    @GetMapping("/editView")
    public String viewEditProduct(Model model, @RequestParam Long productId)
    {

        ResponseEntity<CategoryDto[]> responseCategory = restTemplate.getForEntity(apiGetCategory, CategoryDto[].class);
        CategoryDto[] categoryDtos = responseCategory.getBody();
        model.addAttribute("categoryList", categoryDtos);
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(apiGetDetailProduct)
                .queryParam("productId", "{productId}")
                .encode()
                .toUriString();
        Map<String, Long> params = new HashMap<>();
        params.put("productId", productId);
        ResponseEntity<ProductDetailDto> response =
                restTemplate.getForEntity(urlTemplate,ProductDetailDto.class,params);
        ProductDetailDto product = response.getBody();
        model.addAttribute("product", product);
        return "editProduct";
    }
    @RequestMapping("/edit")
    public String editProduct(Model model, @RequestParam String productId,
                              @RequestParam String productName,
                             @RequestParam String oldPrice, @RequestParam String percentDiscount,
                             @RequestParam int quantity, @RequestParam String shortDescription,
                             @RequestParam String newPrice,
                             @RequestParam String categoryName, @RequestParam("urlImage") MultipartFile multipartFile,
                              HttpServletRequest request
    ) throws IOException {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpSession session = request.getSession();
        JwtResponseDTO jwtResponseDTO = (JwtResponseDTO)session.getAttribute("jwtResponse");
        httpHeaders.set(HttpHeaders.AUTHORIZATION,"Bearer "+jwtResponseDTO.getAccessToken());
        String imagePath = null;
        Path des = FileSystems.getDefault().getPath("imgs/product" + productId);
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        if(!fileName.isEmpty()) {
            if (!Files.exists(des)) {
                Files.createDirectories(des);
            }
            try (InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = des.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ioe) { }
            imagePath = fileName;
        }
        Product product = new Product();
        if (!productId.isEmpty())
        {
            product.setProductId(Long.valueOf(productId));
            product.setProductName(productName);
            product.setShortDescription(shortDescription);
            product.setOldPrice(Double.parseDouble(removeComma(oldPrice)));
            product.setNewPrice(Double.parseDouble(removeComma(newPrice)));
            product.setStatus(1);
            product.setPercentDiscount(Integer.parseInt(percentDiscount));
            product.setUrlImage(imagePath);
            product.setQuantity(quantity);
            product.setCategoryName(categoryName);
            System.out.println(product);
            HttpEntity<Product> httpEntity = new HttpEntity<>(product, httpHeaders);
            ResponseEntity<String> response
                    = restTemplate.exchange(apiEditProduct, HttpMethod.PUT, httpEntity, String.class);
        }
        return "redirect:/";
    }

}

/*
logic sao : 100 sao : 40 = 2.5
 */