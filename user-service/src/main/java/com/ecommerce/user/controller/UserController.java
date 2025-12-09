package com.ecommerce.user.controller;

import com.ecommerce.user.dto.UserRequest;
import com.ecommerce.user.dto.UserResponse;
import com.ecommerce.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 🎮 USER CONTROLLER - The API Entry Point (REST API)
 * 
 * This is the TOP layer - where HTTP requests arrive!
 * 
 * What is a Controller?
 * Think of it as a "receptionist" at a company:
 * - Receives requests from outside (HTTP calls)
 * - Validates the request is proper
 * - Passes work to the Service layer
 * - Returns responses back to the caller
 * 
 * Annotations explained:
 * @RestController: "I handle HTTP requests and return JSON"
 * @RequestMapping: "All my endpoints start with /api/users"
 * @RequiredArgsConstructor: Auto-inject dependencies (UserService)
 * 
 * HTTP Methods (like actions):
 * GET = Retrieve data (like viewing a menu)
 * POST = Create new data (like placing an order)
 * PUT = Update existing data (like changing your order)
 * DELETE = Remove data (like canceling an order)
 * 
 * Status Codes:
 * 200 OK = Success
 * 201 CREATED = Successfully created
 * 404 NOT FOUND = Doesn't exist
 * 400 BAD REQUEST = Invalid data
 * 500 SERVER ERROR = Something broke
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "APIs for managing user accounts")
public class UserController {
    
    private final UserService userService;
    
    /**
     * 📝 CREATE a new user
     * POST /api/users
     * 
     * Example request:
     * POST http://localhost:8081/api/users
     * Body: { "firstName": "John", "lastName": "Doe", "email": "john@email.com", ... }
     */
    @PostMapping
    @Operation(summary = "Create a new user", description = "Creates a new user account")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "User created successfully",
                content = @Content(schema = @Schema(implementation = UserResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "409", description = "Email already exists")
    })
    public ResponseEntity<UserResponse> createUser(
            @Valid @RequestBody UserRequest request) {
        
        UserResponse response = userService.createUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    /**
     * 🔍 GET user by ID
     * GET /api/users/{id}
     * 
     * Example: GET http://localhost:8081/api/users/507f1f77bcf86cd799439011
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieves a user by their unique ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User found",
                content = @Content(schema = @Schema(implementation = UserResponse.class))),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserResponse> getUserById(
            @Parameter(description = "User ID", example = "507f1f77bcf86cd799439011")
            @PathVariable String id) {
        
        UserResponse response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }
    
    /**
     * 📋 GET all users
     * GET /api/users
     * 
     * Example: GET http://localhost:8081/api/users
     */
    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieves a list of all registered users")
    @ApiResponse(responseCode = "200", description = "Users retrieved successfully")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> responses = userService.getAllUsers();
        return ResponseEntity.ok(responses);
    }
    
    /**
     * 🔍 GET user by email
     * GET /api/users/email/{email}
     * 
     * Example: GET http://localhost:8081/api/users/email/john@email.com
     */
    @GetMapping("/email/{email}")
    @Operation(summary = "Get user by email", description = "Retrieves a user by their email address")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User found"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserResponse> getUserByEmail(
            @Parameter(description = "User email", example = "john.doe@email.com")
            @PathVariable String email) {
        
        UserResponse response = userService.getUserByEmail(email);
        return ResponseEntity.ok(response);
    }
    
    /**
     * ✏️ UPDATE existing user
     * PUT /api/users/{id}
     * 
     * Example:
     * PUT http://localhost:8081/api/users/507f1f77bcf86cd799439011
     * Body: { "firstName": "Jane", ... }
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update user", description = "Updates an existing user's information")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User updated successfully"),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<UserResponse> updateUser(
            @Parameter(description = "User ID") @PathVariable String id,
            @Valid @RequestBody UserRequest request) {
        
        UserResponse response = userService.updateUser(id, request);
        return ResponseEntity.ok(response);
    }
    
    /**
     * 🗑️ DELETE user
     * DELETE /api/users/{id}
     * 
     * Example: DELETE http://localhost:8081/api/users/507f1f77bcf86cd799439011
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user", description = "Deletes a user account")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "User deleted successfully"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "User ID") @PathVariable String id) {
        
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
