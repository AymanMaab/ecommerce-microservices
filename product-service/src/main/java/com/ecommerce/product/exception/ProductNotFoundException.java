package com.ecommerce.product.exception;

/**
 * 🚫 Custom Exception for "Product Not Found" scenarios
 */
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
