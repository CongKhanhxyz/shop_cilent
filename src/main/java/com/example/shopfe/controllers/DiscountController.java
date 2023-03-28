package com.example.shopfe.controllers;

import com.example.shopfe.constants.Api;
import com.example.shopfe.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping(value = "/discounts" )
public class DiscountController {

    @Autowired
    private RestTemplate restTemplate;

    private static String apiGetCategory = Api.baseURL + "/api/categories";

    private static String apiGetProductByCategory = Api.baseURL + "/api/products/category";

    private static String apiSaveDiscount = Api.baseURL + "/api/discounts/save";

    private static String apiGetAllProduct = Api.baseURL + "/api/products/all";

    private static String apiGetDetailDiscount = Api.baseURL + "/api/discounts/detail";

    private static String apiGetLastId = Api.baseURL + "/api/discounts/lastId";


    @GetMapping
    public String viewAddDiscount( Model model)
    {
        ResponseEntity<CategoryDto[]> response = restTemplate.getForEntity(apiGetCategory, CategoryDto[].class);
        CategoryDto[] categoryDtos = response.getBody();
        ResponseEntity<Product[]> responsePro = restTemplate.getForEntity(apiGetAllProduct, Product[].class);
        Product[] productDtos = responsePro.getBody();
        model.addAttribute("categoryList", categoryDtos);
        ResponseEntity<ProductByCategory[]> responseProduct =
                restTemplate.getForEntity(apiGetProductByCategory, ProductByCategory[].class);
        ProductByCategory[] productByCategorys = responseProduct.getBody();
        model.addAttribute("products", productDtos);
        model.addAttribute("productByCategorys", productByCategorys);
        return "discountAdd";
    }
    @PostMapping("/create")
    public String addDiscount(@RequestParam String discountName, @RequestParam int discountValue,
                              @RequestParam String discountUnit, @RequestParam String discountMethod,
                              @RequestParam String discountTypes, @RequestParam String discountStart,
                              @RequestParam String discountEnd, @RequestParam String discountProducts,
                              HttpServletRequest request)
    {
        ResponseEntity<Integer> response = restTemplate.getForEntity(apiGetLastId, Integer.class);
        Integer lastDiscountId = response.getBody();
        DiscountProductDto discountProductDto = new DiscountProductDto();
        discountProductDto.setName(discountName);
        discountProductDto.setValue(discountValue);
        discountProductDto.setMethod(discountMethod);
        discountProductDto.setUnit(discountUnit);
        discountProductDto.setStartDate(discountStart);
        discountProductDto.setEndDate(discountEnd);
        System.out.println(discountTypes);
        String arr[] = discountTypes.split(";");
        List<String> categories = new ArrayList<>();
        Arrays.stream(arr).forEach(
                category -> categories.add(category)
        );
        discountProductDto.setCategory(categories);
        String arrProduct[] = discountProducts.split(",");
        List<String> products = new ArrayList<>();
        Arrays.stream(arrProduct).forEach(
                product -> products.add(product)
        );
        discountProductDto.setProducts(products);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpSession session = request.getSession();
        JwtResponseDTO jwtResponseDTO = (JwtResponseDTO)session.getAttribute("jwtResponse");
        httpHeaders.set(HttpHeaders.AUTHORIZATION,"Bearer "+jwtResponseDTO.getAccessToken());
        HttpEntity<DiscountProductDto> httpEntity = new HttpEntity<>(discountProductDto, httpHeaders);
        ResponseEntity<DiscountDto> discountResponse = restTemplate.exchange(apiSaveDiscount, HttpMethod.POST, httpEntity, DiscountDto.class);
        DiscountDto discountDto = discountResponse.getBody();
        return "redirect:/discounts/detail?discountId=" + (lastDiscountId + 1);
    }
    @GetMapping("/detail")
    public String viewDetailDiscount(@RequestParam Long discountId, Model model)
    {
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(apiGetDetailDiscount)
                .queryParam("discountId", "{discountId}")
                .encode()
                .toUriString();
        Map<String, Long> params = new HashMap<>();
        params.put("discountId", discountId);
        ResponseEntity<DiscountDto> response =
                restTemplate.getForEntity(urlTemplate,DiscountDto.class,params);
        model.addAttribute("discount", response.getBody());
        return "detailDiscount";
    }
    @GetMapping("/edit")
    public String viewEditDiscount(@RequestParam Long discountId, Model model)
    {
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(apiGetDetailDiscount)
                .queryParam("discountId", "{discountId}")
                .encode()
                .toUriString();
        Map<String, Long> params = new HashMap<>();
        params.put("discountId", discountId);
        ResponseEntity<DiscountDto> response =
                restTemplate.getForEntity(urlTemplate,DiscountDto.class,params);
        model.addAttribute("discount", response.getBody());
        return "editDiscount";
    }
}
