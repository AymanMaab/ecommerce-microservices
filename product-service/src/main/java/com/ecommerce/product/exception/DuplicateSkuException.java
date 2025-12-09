package com.ecommerce.product.exception;

/**
 * 🚫 Custom Exception for Duplicate SKU scenarios
 */
public class DuplicateSkuException extends RuntimeException {
    public DuplicateSkuException(String message) {
        super(message);
    }
}
