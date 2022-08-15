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
        final String[] allowedURLs = { "http://localhost:3000", "https://threehundreddaysofcode.com" };

        final String[] apiList = {"/me", "/leaderboard", "/problem", "/", "/createProfile", "/updateProfile", "/submitCode" };

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                for (String api : apiList) {
                    registry.addMapping(api).allowedOrigins(allowedURLs);
                }
            }
        };
    }
}

