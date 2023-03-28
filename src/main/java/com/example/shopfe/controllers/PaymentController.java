package com.example.shopfe.controllers;

import com.example.shopfe.constants.Api;
import com.example.shopfe.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.*;

import static java.util.Arrays.sort;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private RestTemplate restTemplate;

    private static String apiGetCartForEach = Api.baseURL + "/api/carts/each";

    private static String apiGetDiscount = Api.baseURL + "/api/discounts/products";

    @PostMapping()
    public String getPaymentView(Model model, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        JwtResponseDTO jwtResponseDTO = (JwtResponseDTO) session.getAttribute("jwtResponse");
        request.getSession().removeAttribute("freeShipValue");
        String[] products = request.getParameterValues("productIds");
        String newProducts = "";
        for (String product : products) {
            newProducts += product;
        }
        String urlTemplate  = UriComponentsBuilder.fromHttpUrl(apiGetCartForEach)
                .queryParam("listProductId", "{listProductId}")
                .queryParam("userId", "{userId}")
                .encode()
                .toUriString();
        Map<String, String> params = new HashMap<>();
        System.out.println(newProducts);
        params.put("listProductId", newProducts);
        params.put("userId",String.valueOf(jwtResponseDTO.getId()));

        String urlTemplateDiscount  = UriComponentsBuilder.fromHttpUrl(apiGetDiscount)
                .queryParam("listProductId", "{listProductId}")
                .encode()
                .toUriString();
        Map<String, String> paramsDiscount = new HashMap<>();
        paramsDiscount.put("listProductId", newProducts);
        ResponseEntity<CartDto[]> response =
                restTemplate.getForEntity(urlTemplate,CartDto[].class, params);
        ResponseEntity<DiscountDto[]> responseDicount =
                restTemplate.getForEntity(urlTemplateDiscount,DiscountDto[].class, paramsDiscount);
        String[] filterList = newProducts.split(",");
        model.addAttribute("total", filterList.length);
        session.setAttribute("listProductIds", products);
        String freeShip = Arrays.toString(request.getParameterValues("freeShip"));
        Integer freeShipValue = null;

            String freeShipModi = freeShip.substring(1, freeShip.length() - 1);
            System.out.println(freeShipModi);
        if (!freeShipModi.equals(""))
        {
            freeShipValue = Integer.parseInt(freeShipModi);
        }
        else {
            freeShipValue = 0;
        }


        model.addAttribute("discounts", responseDicount.getBody());
        LocalDate date = LocalDate.now().plusDays(7);
        session.setAttribute("date", date);
        session.setAttribute("freeShipValue", freeShipValue);
        session.setAttribute("listProductIds", newProducts);
        session.setAttribute("carts", response.getBody());
        return "payment";
    }
}
