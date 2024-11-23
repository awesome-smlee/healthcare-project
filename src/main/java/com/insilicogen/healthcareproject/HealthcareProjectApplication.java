package com.insilicogen.healthcareproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class HealthcareProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(HealthcareProjectApplication.class, args);
    }
}