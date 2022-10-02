package com.code.console;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsoleServiceApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ConsoleServiceApplication.class);
        app.run(args);
    }
}