package com.ecommerce.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 📝 PRODUCT REQUEST DTO - For Creating/Updating Products
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request object for creating or updating a product")
public class ProductRequest {
    
    @NotBlank(message = "SKU is required")
    @Schema(description = "Unique Stock Keeping Unit", example = "LAP-001")
    private String sku;
    
    @NotBlank(message = "Product name is required")
    @Schema(description = "Product name", example = "Laptop")
    private String name;
    
    @Schema(description = "Product description", example = "High-performance gaming laptop")
    private String description;
    
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Schema(description = "Product price", example = "999.99")
    private BigDecimal price;
    
    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock cannot be negative")
    @Schema(description = "Available stock quantity", example = "50")
    private Integer stock;
    
    @NotBlank(message = "Category is required")
    @Schema(description = "Product category", example = "Electronics")
    private String category;
    
    @Schema(description = "Product image URL", example = "https://example.com/laptop.jpg")
    private String imageUrl;
}
