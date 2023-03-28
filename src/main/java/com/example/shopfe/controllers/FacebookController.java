package com.example.shopfe.controllers;

import com.example.shopfe.constants.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/admin")
public class FacebookController {

    @Autowired
    private RestTemplate restTemplate;

    public static String apiLogin = Api.baseURL+"";
//    public static String API_REGISTER = Api.baseURL+"/register";
//
//    //    @GetMapping
////    public String getPageLogin()
////    {
////        return "Login";
////    }
//    @PostMapping("/login")
//    public String login(Model model, HttpServletRequest request) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
////        User user = new User();
////        user.setUsername(request.getParameter("username"));
////        user.setPassword(request.getParameter("password"));
//        try {
//            HttpEntity<User> httpEntity = new HttpEntity<>( httpHeaders);
//            ResponseEntity<JwtResponseDTO> jwtResponse
//                    = restTemplate.exchange(apiLogin, HttpMethod.POST, httpEntity, JwtResponseDTO.class);
//            System.out.println(jwtResponse.getBody());
//
//            System.out.println((JwtResponseDTO) jwtResponse.getBody());
////            request.getSession().setAttribute("jwtResponse", "a");
//            request.getSession().setAttribute("jwtResponse", (JwtResponseDTO) jwtResponse.getBody());
//        }
//        catch (HttpClientErrorException ex){
//            model.addAttribute("error",ex.getResponseBodyAsString());
//            model.addAttribute("hasLoginErrors", true);
//            model.addAttribute("user",new User());
////            model.addAttribute("un",user.getUsername());
////            model.addAttribute("pw",user.getPassword());
//            return "login";
//        }
//        return "redirect:/";
//
//    }

    @GetMapping()
    public String viewHomeAd()
    {

        return "sale";
    }
}
