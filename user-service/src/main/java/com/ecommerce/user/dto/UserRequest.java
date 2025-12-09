package com.ecommerce.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 📝 USER REQUEST DTO - Data Transfer Object for Creating/Updating Users
 * 
 * DTOs vs Entities - The Key Difference:
 * - Entity (User.java): What we STORE in the database (includes ID, timestamps, etc.)
 * - DTO (UserRequest.java): What we RECEIVE from API calls (no ID, we generate that!)
 * 
 * Why separate them?
 * 1. Security: Don't let users set their own ID or timestamps
 * 2. Validation: Check if data is correct BEFORE saving
 * 3. Flexibility: API format can differ from database format
 * 
 * Real-life analogy: 
 * DTO = Order form you fill out at a restaurant
 * Entity = The actual recipe stored in the kitchen
 * 
 * Validation Annotations:
 * @NotBlank: Field cannot be empty or just spaces
 * @Email: Must be a valid email format
 * @Pattern: Must match a specific pattern (phone number format)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request object for creating or updating a user")
public class UserRequest {
    
    @NotBlank(message = "First name is required")
    @Schema(description = "User's first name", example = "John")
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    @Schema(description = "User's last name", example = "Doe")
    private String lastName;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Schema(description = "User's email address", example = "john.doe@email.com")
    private String email;
    
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    @Schema(description = "User's phone number", example = "1234567890")
    private String phone;
    
    @Schema(description = "User's address", example = "123 Main St, City, State")
    private String address;
}
