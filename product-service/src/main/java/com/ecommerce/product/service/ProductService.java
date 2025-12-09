package com.ecommerce.product.service;

import com.ecommerce.product.dto.ProductRequest;
import com.ecommerce.product.dto.ProductResponse;

import java.util.List;

/**
 * 📋 PRODUCT SERVICE INTERFACE
 * 
 * This interface defines the contract for product operations.
 * Following Interface Segregation Principle (SOLID).
 * 
 * Why use interfaces?
 * - Easy to mock for testing
 * - Can have multiple implementations
 * - Loose coupling between layers
 */
public interface ProductService {
    
    ProductResponse createProduct(ProductRequest request);
    
    ProductResponse getProductById(String id);
    
    ProductResponse getProductBySku(String sku);
    
    List<ProductResponse> getAllProducts();
    
    List<ProductResponse> getProductsByCategory(String category);
    
    List<ProductResponse> searchProductsByName(String name);
    
    ProductResponse updateProduct(String id, ProductRequest request);
    
    void deleteProduct(String id);
    
    boolean updateStock(String id, int quantity);
}
