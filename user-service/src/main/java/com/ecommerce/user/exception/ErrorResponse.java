package com.ecommerce.user.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 📋 ERROR RESPONSE - Standard format for all error messages
 * 
 * Why standardize errors?
 * - API consumers always know what to expect
 * - Easy to parse on frontend
 * - Professional and consistent
 * 
 * Example error response:
 * {
 *   "timestamp": "2024-01-15T10:30:00",
 *   "status": 404,
 *   "error": "Not Found",
 *   "message": "User not found with ID: 123",
 *   "path": "/api/users/123"
 * }
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
