package com.ecommerce.user.exception;

/**
 * 🚫 Custom Exception for "User Not Found" scenarios
 * 
 * Why custom exceptions?
 * Instead of generic errors, we create specific exceptions that:
 * 1. Make code more readable
 * 2. Allow specific error handling
 * 3. Provide better error messages to users
 * 
 * Real-life analogy:
 * Instead of saying "Error!", we say exactly what went wrong:
 * "User not found with ID: 123"
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
