package com.example.shopfe.controllers;

import com.example.shopfe.constants.Api;
import com.example.shopfe.model.ProductReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/report")
public class AdminController {

    @Autowired
    private RestTemplate restTemplate;

    private static String apiGetProductByDate = Api.baseURL + "/api/orders/report";

    @GetMapping("/view")
    public String getViewReport()
    {
        return "sale";
    }
    @PostMapping("/view")
    public String getViewReportByDate(Model model,
                                      @RequestParam String startDate, @RequestParam String endDate)
    {
        String urlTemplateReviews = UriComponentsBuilder.fromHttpUrl(apiGetProductByDate)
                .queryParam("startDate", "{startDate}")
                .queryParam("endDate", "{endDate}")
                .encode()
                .toUriString();
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        Map<String, String> paramForReview = new HashMap<>();
        paramForReview.put("startDate", startDate);
        paramForReview.put("endDate", endDate);
        ResponseEntity<ProductReport[]> responseReview =
                restTemplate.getForEntity(urlTemplateReviews,ProductReport[].class,paramForReview);
        ProductReport[] productReports = responseReview.getBody();
        model.addAttribute("productReports", productReports);
        return "sale";
    }
}
