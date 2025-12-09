package com.ecommerce.product.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 📚 SWAGGER CONFIGURATION - API Documentation Setup for Product Service
 */
@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI productServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Product Service API")
                        .description("RESTful API for managing product catalog and inventory")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("E-Commerce Team")
                                .email("support@ecommerce.com")
                                .url("https://github.com/AymanMaab/ecommerce-microservices"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8082")
                                .description("Development Server"),
                        new Server()
                                .url("http://localhost:8082")
                                .description("Production Server")
                ));
    }
}
