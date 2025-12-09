package com.ecommerce.user.exception;

/**
 * 🚫 Custom Exception for Duplicate Email scenarios
 */
public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String message) {
        super(message);
    }
}
