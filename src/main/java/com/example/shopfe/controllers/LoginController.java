package com.example.shopfe.controllers;

import com.example.shopfe.model.JwtResponseDTO;
import com.example.shopfe.model.Role;
import com.example.shopfe.model.User;
import com.example.shopfe.constants.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.*;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/account")
public class LoginController {
    @Autowired
    private RestTemplate restTemplate;

    public static String apiLogin = Api.baseURL+"/login";
    public static String API_REGISTER = Api.baseURL+"/register";

    @PostMapping("/login")
    public String login(Model model, HttpServletRequest request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        try {
            HttpEntity<User> httpEntity = new HttpEntity<>(user, httpHeaders);
            ResponseEntity<JwtResponseDTO> jwtResponse
                    = restTemplate.exchange(apiLogin, HttpMethod.POST, httpEntity, JwtResponseDTO.class);
            System.out.println(jwtResponse.getBody());

            System.out.println((JwtResponseDTO) jwtResponse.getBody());
            request.getSession().setAttribute("jwtResponse", (JwtResponseDTO) jwtResponse.getBody());
        }
        catch (HttpClientErrorException ex){
            model.addAttribute("error",ex.getResponseBodyAsString());
            model.addAttribute("hasLoginErrors", true);
            model.addAttribute("user",new User());
            model.addAttribute("un",user.getUsername());
            model.addAttribute("pw",user.getPassword());
            return "login";
        }
        return "redirect:/";

    }


    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("hasErrors", true);
            return "register";
        } else {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            Set<Role> roles = new HashSet<>();
            Role roleClient = new Role();
            roleClient.setName("USER");
            roles.add(roleClient);
            user.setRoles(roles);
            try {
                System.out.println(user.getFullName());
                System.out.println(user.getUsername());
                System.out.println(user.getPassword());

                HttpEntity<User> httpEntity = new HttpEntity<>(user, httpHeaders);
                ResponseEntity<JwtResponseDTO> jwtResponse
                        = restTemplate.exchange(API_REGISTER, HttpMethod.POST, httpEntity, JwtResponseDTO.class);

                request.getSession().setAttribute("jwtResponse", (JwtResponseDTO) jwtResponse.getBody());
            }catch (HttpClientErrorException ex){
                model.addAttribute("registerError",ex.getResponseBodyAsString());
                model.addAttribute("error", true);
                model.addAttribute("user",new User());
                model.addAttribute("fn",user.getFullName());
                model.addAttribute("un",user.getUsername());
                model.addAttribute("pw",user.getPassword());
                return "register";
            }
            return "redirect:/user";
        }
    }

    @GetMapping("/sign-out")
    public String signOut(HttpServletRequest request) {
        request.getSession().removeAttribute("jwtResponse");
        return "redirect:/";
    }
}