package com.ecommerce.product.repository;

import com.ecommerce.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 🗄️ PRODUCT REPOSITORY - Database Access Layer
 * 
 * Custom query methods:
 * - findBySku() - Find by unique product code
 * - findByCategory() - Filter by category
 * - findByNameContainingIgnoreCase() - Search products by name
 * - findByPriceBetween() - Find products in price range
 * - findByStockGreaterThan() - Find in-stock products
 */
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    
    /**
     * Find product by SKU (unique product code)
     */
    Optional<Product> findBySku(String sku);
    
    /**
     * Check if SKU already exists
     */
    boolean existsBySku(String sku);
    
    /**
     * Find all products in a category
     */
    List<Product> findByCategory(String category);
    
    /**
     * Search products by name (case-insensitive, partial match)
     * Example: searching "lap" will find "Laptop", "laptop stand", etc.
     */
    List<Product> findByNameContainingIgnoreCase(String name);
    
    /**
     * Find products within price range
     */
    @Query("{ 'price': { $gte: ?0, $lte: ?1 } }")
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
    
    /**
     * Find products with stock greater than specified amount
     * Useful for finding available products
     */
    List<Product> findByStockGreaterThan(Integer minStock);
}
