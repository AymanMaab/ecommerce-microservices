package com.ecommerce.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 🚀 PRODUCT SERVICE - Main Application Entry Point
 * 
 * This microservice handles:
 * - Product catalog management
 * - Inventory tracking
 * - Price management
 * - Product search and filtering
 */
@SpringBootApplication
public class ProductServiceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
        System.out.println("✅ Product Service is running! Visit: http://localhost:8082/swagger-ui.html");
    }
}
