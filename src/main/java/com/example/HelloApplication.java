package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@SpringBootApplication

public class HelloApplication {

    public static void main(String[] args) {

        SpringApplication.run(HelloApplication.class, args);


    }



}