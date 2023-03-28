package com.example.shopfe.controllers;

import com.example.shopfe.constants.Api;
import com.example.shopfe.model.AddressDto;
import com.example.shopfe.model.CartDto;
import com.example.shopfe.model.JwtResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private RestTemplate restTemplate;

    private static String apiGetCart = Api.baseURL + "/api/carts";

    private static String apiGetAddressDefault = Api.baseURL + "/api/addresses/default";

    private static String apiGetAddressDetail = Api.baseURL + "/api/addresses/detail";


    @GetMapping
    public String viewCart( Model model, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        JwtResponseDTO jwtResponseDTO = (JwtResponseDTO) session.getAttribute("jwtResponse");

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(apiGetCart)
                .queryParam("userId", "{userId}")
                .encode()
                .toUriString();
        Map<String, Long> params = new HashMap<>();
        params.put("userId", jwtResponseDTO.getId());
        ResponseEntity<CartDto[]> response =
                restTemplate.getForEntity(urlTemplate,CartDto[].class,params);
        CartDto[] cartDtos = response.getBody();
        for (CartDto cartDto : cartDtos)
        {
            model.addAttribute("total", cartDto.getTotal());
        }

        String urlTemplateAddressDefault = UriComponentsBuilder.fromHttpUrl(apiGetAddressDefault)
                .queryParam("userId", "{userId}")
                .encode()
                .toUriString();
        ResponseEntity<AddressDto> responseAddressDefault =
                restTemplate.getForEntity(urlTemplateAddressDefault,AddressDto.class,params);
        if (responseAddressDefault.hasBody())
        {
            AddressDto addressDto = responseAddressDefault.getBody();
            request.getSession().setAttribute("address", (AddressDto) responseAddressDefault.getBody());
            model.addAttribute("address", addressDto);
        }

        model.addAttribute("carts", cartDtos);
        return "cartDetail";
    }
    @GetMapping("/{addressId}")
    public String viewCartChange(@PathVariable Long addressId, Model model, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        JwtResponseDTO jwtResponseDTO = (JwtResponseDTO) session.getAttribute("jwtResponse");

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(apiGetCart)
                .queryParam("userId", "{userId}")
                .encode()
                .toUriString();
        Map<String, Long> params = new HashMap<>();
        params.put("userId", jwtResponseDTO.getId());
        ResponseEntity<CartDto[]> response =
                restTemplate.getForEntity(urlTemplate,CartDto[].class,params);
        CartDto[] cartDtos = response.getBody();
        for (CartDto cartDto : cartDtos)
        {
            model.addAttribute("total", cartDto.getTotal());
        }

        String urlTemplateAddress = UriComponentsBuilder.fromHttpUrl(apiGetAddressDetail)
                .queryParam("addressId", "{addressId}")
                .encode()
                .toUriString();
        Map<String, Long> paramsAddress = new HashMap<>();
        paramsAddress.put("addressId", addressId);
        ResponseEntity<AddressDto> responseAddress =
                restTemplate.getForEntity(urlTemplateAddress,AddressDto.class,paramsAddress);
        request.getSession().removeAttribute("address");
            AddressDto addressDto = responseAddress.getBody();
            request.getSession().setAttribute("address", (AddressDto) responseAddress.getBody());
            model.addAttribute("address", addressDto);
        model.addAttribute("carts", cartDtos);
        return "cartDetail";
    }
}
