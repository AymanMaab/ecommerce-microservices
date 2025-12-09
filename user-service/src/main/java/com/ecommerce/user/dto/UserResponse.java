package com.ecommerce.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 📤 USER RESPONSE DTO - What We Send Back to API Callers
 * 
 * Why a separate response DTO?
 * 1. Control what information we expose (maybe hide sensitive data)
 * 2. Add computed fields (like fullName)
 * 3. Format data differently than it's stored
 * 
 * Real-life analogy:
 * You ask a librarian for a book (request)
 * They give you the book + receipt + due date (response with extra info)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response object containing user details")
public class UserResponse {
    
    @Schema(description = "User's unique identifier", example = "507f1f77bcf86cd799439011")
    private String id;
    
    @Schema(description = "User's first name", example = "John")
    private String firstName;
    
    @Schema(description = "User's last name", example = "Doe")
    private String lastName;
    
    @Schema(description = "User's full name", example = "John Doe")
    private String fullName;
    
    @Schema(description = "User's email address", example = "john.doe@email.com")
    private String email;
    
    @Schema(description = "User's phone number", example = "1234567890")
    private String phone;
    
    @Schema(description = "User's address", example = "123 Main St, City, State")
    private String address;
    
    @Schema(description = "Account creation timestamp")
    private LocalDateTime createdAt;
    
    @Schema(description = "Last update timestamp")
    private LocalDateTime updatedAt;
}
