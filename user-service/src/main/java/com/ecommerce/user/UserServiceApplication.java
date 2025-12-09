package com.ecommerce.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 🚀 USER SERVICE - Main Application Entry Point
 * 
 * Think of this as the "ignition key" that starts your User Service microservice.
 * 
 * @SpringBootApplication does 3 magical things:
 * 1. @Configuration: Tells Spring "this class has configuration"
 * 2. @EnableAutoConfiguration: Spring automatically configures beans based on dependencies
 * 3. @ComponentScan: Scans this package and sub-packages for components
 * 
 * Real-life analogy: Like turning on the lights in a building - everything powers up automatically!
 */
@SpringBootApplication
public class UserServiceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
        System.out.println("✅ User Service is running! Visit: http://localhost:8081/swagger-ui.html");
    }
}
