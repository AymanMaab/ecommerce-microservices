package com.ecommerce.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * 👤 USER ENTITY - The Database Model
 * 
 * This represents a REAL USER in our MongoDB database.
 * 
 * Annotations explained like you're 5:
 * @Document: "Hey MongoDB, save this as a 'users' collection"
 * @Data: Lombok creates getters, setters, toString automatically (less typing!)
 * @Builder: Lets you create objects like: User.builder().name("John").build()
 * @NoArgsConstructor: Creates empty constructor: new User()
 * @AllArgsConstructor: Creates full constructor: new User(id, name, email...)
 * 
 * Real-life analogy: This is like a customer registration form at a gym
 */
@Document(collection = "users")  // This will be the table name in MongoDB
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id  // MongoDB's unique identifier (like a Social Security Number)
    private String id;
    
    private String firstName;
    private String lastName;
    
    @Indexed(unique = true)  // Email must be unique (no duplicates allowed!)
    private String email;
    
    private String phone;
    private String address;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    /**
     * Helper method to get full name
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
