package com.ecommerce.user.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecommerce.user.dto.UserRequest;
import com.ecommerce.user.dto.UserResponse;
import com.ecommerce.user.exception.DuplicateEmailException;
import com.ecommerce.user.exception.UserNotFoundException;
import com.ecommerce.user.model.User;
import com.ecommerce.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 💼 USER SERVICE - Business Logic Layer
 * 
 * This is where the BRAIN of your application lives!
 * The Service layer contains all business rules and logic.
 * 
 * Layer Architecture (like a sandwich):
 * Controller (top bread) → receives HTTP requests
 * Service (filling) → processes business logic  ← WE ARE HERE
 * Repository (bottom bread) → talks to database
 * 
 * Why separate Service from Controller?
 * 1. Reusability: Same logic can be used by different controllers
 * 2. Testing: Easy to test business logic without HTTP
 * 3. Clean Code: Each layer has ONE job
 * 
 * Dependency Injection explained:
 * @RequiredArgsConstructor (from Lombok) creates a constructor that injects dependencies
 * Instead of: UserRepository repo = new UserRepository() ❌
 * Spring does: "I'll create and give you the repository" ✅
 * 
 * Real-life analogy:
 * You (Controller) ask the chef (Service) to make a burger
 * Chef uses the pantry (Repository) to get ingredients
 * You don't go to the pantry yourself!
 */
@Service
@RequiredArgsConstructor  // Creates constructor with all 'final' fields
@Slf4j  // Gives us a logger: log.info(), log.error(), etc.
public class UserService {
    
    // This will be automatically injected by Spring (Dependency Injection)
    private final UserRepository userRepository;
    
    /**
     * 📝 CREATE a new user
     * 
     * Steps:
     * 1. Check if email already exists
     * 2. Convert DTO to Entity
     * 3. Add timestamps
     * 4. Save to database
     * 5. Convert Entity back to DTO and return
     */
    public UserResponse createUser(UserRequest request) {
        log.info("Creating user with email: {}", request.getEmail());
        
        // Business Rule: No duplicate emails!
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException("Email already exists: " + request.getEmail());
        }
        
        // Convert DTO → Entity
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        
        if (user == null) {
            throw new IllegalArgumentException("Failed to create user from request");
        }
        
        // Save to database
        User savedUser = userRepository.save(user);
        log.info("User created successfully with ID: {}", savedUser.getId());
        
        // Convert Entity → Response DTO
        return mapToResponse(savedUser);
    }
    
    /**
     * 🔍 GET user by ID
     */
    public UserResponse getUserById(String id) {
        log.info("Fetching user with ID: {}", id);
        
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
        
        return mapToResponse(user);
    }
    
    /**
     * 📋 GET all users
     */
    public List<UserResponse> getAllUsers() {
        log.info("Fetching all users");
        
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    /**
     * 🔍 GET user by email
     */
    public UserResponse getUserByEmail(String email) {
        log.info("Fetching user with email: {}", email);
        
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        
        return mapToResponse(user);
    }
    
    /**
     * ✏️ UPDATE existing user
     */
    public UserResponse updateUser(String id, UserRequest request) {
        log.info("Updating user with ID: {}", id);
        
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
        
        // Check if email is being changed to a duplicate
        if (!existingUser.getEmail().equals(request.getEmail()) && 
            userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException("Email already exists: " + request.getEmail());
        }
        
        // Update fields
        existingUser.setFirstName(request.getFirstName());
        existingUser.setLastName(request.getLastName());
        existingUser.setEmail(request.getEmail());
        existingUser.setPhone(request.getPhone());
        existingUser.setAddress(request.getAddress());
        existingUser.setUpdatedAt(LocalDateTime.now());
        
        User updatedUser = userRepository.save(existingUser);
        log.info("User updated successfully: {}", id);
        
        return mapToResponse(updatedUser);
    }
    
    /**
     * 🗑️ DELETE user
     */
    @SuppressWarnings("null")
    public void deleteUser(String id) {
        log.info("Deleting user with ID: {}", id);
        
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with ID: " + id);
        }
        
        userRepository.deleteById(id);
        log.info("User deleted successfully: {}", id);
    }
    
    /**
     * 🔧 Helper method: Convert Entity to Response DTO
     * 
     * Why a separate method?
     * - Keeps code DRY (Don't Repeat Yourself)
     * - Easy to maintain in one place
     * - Can add formatting/calculations here
     */
    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .fullName(user.getFullName())  // Computed field!
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
