package com.example.shopfe.controllers;

import com.example.shopfe.constants.Api;
import com.example.shopfe.model.JwtResponseDTO;
import com.example.shopfe.model.OrderDto;
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
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    private static String apiGetLastId = Api.baseURL + "/api/orders/lastId";

    private static String apiGetDetailCart = Api.baseURL + "/api/orders/detail";

    private static String apiGetAllOrder = Api.baseURL + "/api/orders/all";


    @GetMapping
    public String orderSuccess(Model model)
    {
        ResponseEntity<Long> responseLastId = restTemplate.getForEntity(apiGetLastId,Long.class);
        Long id = responseLastId.getBody() + 1L;
        model.addAttribute("orderId", id);
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(apiGetDetailCart)
                .queryParam("orderId", "{orderId}")
                .encode()
                .toUriString();
        Map<String, Long> params = new HashMap<>();
        params.put("orderId", id);
        ResponseEntity<OrderDto> response =
                restTemplate.getForEntity(urlTemplate,OrderDto.class,params);
        OrderDto orderDto = response.getBody();
        model.addAttribute("order", orderDto);
        return "orderSuccess";
    }
    @GetMapping("/view")
    public String viewMyOrder(Model model, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        JwtResponseDTO jwtResponseDTO = (JwtResponseDTO) session.getAttribute("jwtResponse");

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(apiGetAllOrder)
                .queryParam("userId", "{userId}")
                .encode()
                .toUriString();
        Map<String, Long> params = new HashMap<>();
        params.put("userId", jwtResponseDTO.getId());
        ResponseEntity<OrderDto[]> response =
                restTemplate.getForEntity(urlTemplate,OrderDto[].class,params);
        OrderDto[] orderDtos = response.getBody();
        model.addAttribute("orders", orderDtos);
        return "myOrder";
    }
}
