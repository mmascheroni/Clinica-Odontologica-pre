package com.integrador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class IntegradorApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntegradorApplication.class, args);
    }

}
