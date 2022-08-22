package com.code.master.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MasterServiceApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MasterServiceApplication.class);
        app.run(args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        final String[] allowedURLs = {
                "http://localhost:3000",
                "https://threehundreddaysofcode.com/",        "http://threehundreddaysofcode.com",
                "https://www.threehundreddaysofcode.com/", "http://www.threehundreddaysofcode.com",
                "https://code-api.threehundreddaysofcode.com/", "http://code-api.threehundreddaysofcode.com",
                "https://www.code-api.threehundreddaysofcode.com/", "http://www.code-api.threehundreddaysofcode.com",
                "https://master.d1ai8g2pfn1x6u.amplifyapp.com"
        };

        final String[] apiList = {
                "/me", "/leaderBoard", "/problem", "/",
                "/createProfile", "/updateProfile", "/submitCode", "/mySubmissions",
                "/submissions"
        };

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                for (String api : apiList) {
                    registry.addMapping(api).allowedOrigins(allowedURLs);
                    final String googleAPI = "/google" + api;
                    registry.addMapping(googleAPI).allowedOrigins(allowedURLs);
                }
            }
        };
    }
}

