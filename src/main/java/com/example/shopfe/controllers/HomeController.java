package com.example.shopfe.controllers;

import com.example.shopfe.constants.Api;
import com.example.shopfe.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
@RequestMapping("/")
public class HomeController {

    @Autowired
    private RestTemplate restTemplate;

    private static String apiGetTotalPage = Api.baseURL + "/api/products/counts";

    private static String apiGetCart = Api.baseURL + "/api/carts";

    @GetMapping()
    public String returnHome(Model model, HttpServletRequest request)
    {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpSession session = request.getSession();
        JwtResponseDTO jwtResponseDTO = (JwtResponseDTO)session.getAttribute("jwtResponse");
//            httpHeaders.set(HttpHeaders.AUTHORIZATION,"Bearer "+jwtResponseDTO.getAccessToken());
            ResponseEntity<Integer> response = restTemplate.getForEntity(apiGetTotalPage,Integer.class);

            Integer totalProducts = response.getBody();

            model.addAttribute("totalProducts",totalProducts);
            if (jwtResponseDTO != null)
            {
                String urlTemplate = UriComponentsBuilder.fromHttpUrl(apiGetCart)
                        .queryParam("userId", "{userId}")
                        .encode()
                        .toUriString();
                Map<String, Long> params = new HashMap<>();
                params.put("userId", jwtResponseDTO.getId());
                ResponseEntity<CartDto[]> responseCart =
                        restTemplate.getForEntity(urlTemplate,CartDto[].class,params);
                CartDto[] cartDtos = responseCart.getBody();
                for (CartDto cartDto : cartDtos)
                {
                    model.addAttribute("total", cartDto.getTotal());
                }

                model.addAttribute("carts", cartDtos);
            }

        return "home";
    }
    @GetMapping("login")
    public String returnLogin()
    {
        return "login";
    }
    @GetMapping("register")
    public String returnRegister(Model model)
    {
        model.addAttribute("user",new User());
        return "register";
    }
}
