package com.example.shopfe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CinemaClientApplication implements CommandLineRunner  {
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
    public static void main(String[] args) {
        SpringApplication.run(CinemaClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        String x = "[1, 2, 3]";
//
//        String y = x.substring(1, x.length() - 1);
//        String arr[] = y.split(", ");
//        Arrays.stream(arr).forEach(
//                z -> System.out.println(Long.parseLong(z))
//        );
    }

//    @Override
//    public void run(String... args) throws Exception {
//        float x = 8;
//        float y = 9;
//        System.out.println(x / y);
//    }
}
