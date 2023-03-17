package com.mycompany.coffeeshop;


import com.mycompany.coffeeshop.Config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AppConfig.class)
public class CoffeeshopApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoffeeshopApplication.class, args);
    }
}