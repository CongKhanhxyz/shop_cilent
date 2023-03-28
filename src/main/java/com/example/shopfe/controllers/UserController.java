package com.example.shopfe.controllers;

import com.example.shopfe.constants.Api;
import com.example.shopfe.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    private static String apiGetUser = Api.baseURL + "/api/users";

    private static String apiGetAddress = Api.baseURL + "/api/addresses";

    private static String apiGetProvince = Api.baseURL + "/api/addresses/province";

    private static String apiGetLastId = Api.baseURL + "/api/users/count";

    private static String apiUpdateUser = Api.baseURL + "/api/users";


    @GetMapping("/users")
    public String viewEditProfile(@RequestParam Long userId, Model model)
    {
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(apiGetUser)
                .queryParam("userId", "{userId}")
                .encode()
                .toUriString();
        Map<String, Long> params = new HashMap<>();
        params.put("userId", userId);
        ResponseEntity<UserDetailDto> response =
                restTemplate.getForEntity(urlTemplate,UserDetailDto.class,params);
        UserDetailDto userDetailDto = response.getBody();
        ResponseEntity<ProvinceDto[]> responseProvince = restTemplate.getForEntity(apiGetProvince,ProvinceDto[].class);
        ProvinceDto[] provinces = responseProvince.getBody();
        model.addAttribute("user", userDetailDto);
        model.addAttribute("provinces", provinces);

        return "editProfile";
    }
    @PostMapping("/users/update")
    public String updateUser(@RequestParam Long userId,
                             @RequestParam String firstname, @RequestParam String lastname,
                             @RequestParam String phone, @RequestParam String province,
                             @RequestParam String district, @RequestParam String ward,
                             @RequestParam("urlImageAvatar") MultipartFile multipartFile,
                             HttpServletRequest request) throws IOException {
        String address = ward + " " + district + " " + province;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpSession session = request.getSession();
        JwtResponseDTO jwtResponseDTO = (JwtResponseDTO)session.getAttribute("jwtResponse");
        httpHeaders.set(HttpHeaders.AUTHORIZATION,"Bearer "+jwtResponseDTO.getAccessToken());
        ResponseEntity<Long> responseLastId = restTemplate.getForEntity(apiGetLastId,Long.class);
        Long lastId = responseLastId.getBody();
        String user = "user"+lastId;
        UserDetailDto userDetailDto = new UserDetailDto();

        String imagePath = null;
        if (!multipartFile.isEmpty()) {
            Path des = FileSystems.getDefault().getPath("imgs/" + user);
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            if (!fileName.isEmpty()) {
                if (!Files.exists(des)) {
                    Files.createDirectories(des);
                }
                try (InputStream inputStream = multipartFile.getInputStream()) {
                    Path filePath = des.resolve(fileName);
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ioe) {
                }
                imagePath = fileName;
            }
            userDetailDto.setUrlImageAvatar(imagePath);
        }
        userDetailDto.setUserId(userId);
        userDetailDto.setLastname(lastname);
        userDetailDto.setFirstname(firstname);
        userDetailDto.setPhone(phone);
        userDetailDto.setAddress(address);
        userDetailDto.setWardName(ward);
        HttpEntity<UserDetailDto> httpEntity = new HttpEntity<>(userDetailDto, httpHeaders);
        ResponseEntity<String> response
                = restTemplate.exchange(apiUpdateUser, HttpMethod.POST, httpEntity, String.class);
        return "redirect:/users?userId=" + userId;
    }
}
