package com.example.shopfe.controllers;

import com.example.shopfe.constants.Api;
import com.example.shopfe.model.JwtResponseDTO;
import com.example.shopfe.model.ReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private RestTemplate restTemplate;

    private static String apiSaveReview = Api.baseURL + "/api/reviews";

    private static String apiGetLastId = Api.baseURL + "/api/reviews/count";


    @GetMapping()
    public String getViewReview(@RequestParam Long orderId, HttpServletRequest request)
    {
        return "review";
    }
    @PostMapping("/save")
    public String saveReview(@RequestParam String content,
                             @RequestParam String vote,
                             @RequestParam String orderId,
                             @RequestParam String productId,
                             @RequestParam("urlImage") MultipartFile multipartFile,
                             HttpServletRequest request) throws IOException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpSession session = request.getSession();
        JwtResponseDTO jwtResponseDTO = (JwtResponseDTO)session.getAttribute("jwtResponse");
        httpHeaders.set(HttpHeaders.AUTHORIZATION,"Bearer "+jwtResponseDTO.getAccessToken());
        ResponseEntity<Long> responseLastId = restTemplate.getForEntity(apiGetLastId,Long.class);
        String  reviewId = null;
        if (responseLastId.hasBody())
        {
            Long lastId = responseLastId.getBody();
            reviewId = "review"+(lastId + 1);
        }
        else {
            reviewId =  "review"+ 1;
        }
        String imagePath = null;
        Path des = FileSystems.getDefault().getPath("imgs/reviews/" + reviewId);
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
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setReviewText(content);
        System.out.println(vote);
        reviewDto.setStar(Integer.parseInt(vote));
        reviewDto.setUrlImage(imagePath);
        reviewDto.setOrderId(Long.valueOf(orderId));
        HttpEntity<ReviewDto> httpEntity = new HttpEntity<>(reviewDto, httpHeaders);
        ResponseEntity<String> response
                = restTemplate.exchange(apiSaveReview, HttpMethod.POST, httpEntity, String.class);
        return "redirect:/products/detail?productId="+productId;
    }
}
