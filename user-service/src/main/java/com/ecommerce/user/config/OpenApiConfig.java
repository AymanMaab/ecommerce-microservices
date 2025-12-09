package com.ecommerce.user.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 📚 SWAGGER CONFIGURATION - API Documentation Setup
 * 
 * What is Swagger/OpenAPI?
 * It's an AUTOMATIC documentation tool that:
 * 1. Generates interactive API documentation
 * 2. Creates a web UI to TEST your APIs (no Postman needed!)
 * 3. Shows all endpoints, request/response formats, and examples
 * 
 * Real-life analogy:
 * Swagger = Instruction manual for your API
 * Like a restaurant menu showing all dishes with descriptions!
 * 
 * After starting the app, visit:
 * http://localhost:8081/swagger-ui.html
 * 
 * You'll see a beautiful interface where you can:
 * - See all API endpoints
 * - Try them out directly in the browser
 * - See request/response examples
 */
@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI userServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("User Service API")
                        .description("RESTful API for managing user accounts in E-Commerce system")
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
                                .url("http://localhost:8081")
                                .description("Development Server"),
                        new Server()
                                .url("http://localhost:8081")
                                .description("Production Server")
                ));
    }
}
