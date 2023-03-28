package com.example.shopfe.controllers;

import com.example.shopfe.constants.Api;
import com.example.shopfe.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private RestTemplate restTemplate;

    private static String apiGetProvince = Api.baseURL + "/api/addresses/province";

    private static String apiGetAddress = Api.baseURL + "/api/addresses";



    @GetMapping
    public String viewAddress(Model model, HttpServletRequest request)
    {
      ;
        HttpSession session = request.getSession();
        JwtResponseDTO jwtResponseDTO = (JwtResponseDTO) session.getAttribute("jwtResponse");

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(apiGetAddress)
                .queryParam("userId", "{userId}")
                .encode()
                .toUriString();
        Map<String, Long> params = new HashMap<>();
        params.put("userId", jwtResponseDTO.getId());
        ResponseEntity<AddressDto[]> response =
                restTemplate.getForEntity(urlTemplate,AddressDto[].class,params);
        AddressDto[] addressList = response.getBody();

        ResponseEntity<ProvinceDto[]> responseProvince = restTemplate.getForEntity(apiGetProvince,ProvinceDto[].class);
        ProvinceDto[] provinces = responseProvince.getBody();
        model.addAttribute("provinces", provinces);
        model.addAttribute("addresses", addressList);
        model.addAttribute("size", addressList.length);
        return "changeAddress";
    }

}
