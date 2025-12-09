package com.ecommerce.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 📤 PRODUCT RESPONSE DTO - What We Send Back
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response object containing product details")
public class ProductResponse {
    
    @Schema(description = "Product unique identifier", example = "507f1f77bcf86cd799439011")
    private String id;
    
    @Schema(description = "Stock Keeping Unit", example = "LAP-001")
    private String sku;
    
    @Schema(description = "Product name", example = "Laptop")
    private String name;
    
    @Schema(description = "Product description", example = "High-performance gaming laptop")
    private String description;
    
    @Schema(description = "Product price", example = "999.99")
    private BigDecimal price;
    
    @Schema(description = "Available stock", example = "50")
    private Integer stock;
    
    @Schema(description = "Stock status", example = "true")
    private boolean inStock;
    
    @Schema(description = "Product category", example = "Electronics")
    private String category;
    
    @Schema(description = "Product image URL")
    private String imageUrl;
    
    @Schema(description = "Creation timestamp")
    private LocalDateTime createdAt;
    
    @Schema(description = "Last update timestamp")
    private LocalDateTime updatedAt;
}
