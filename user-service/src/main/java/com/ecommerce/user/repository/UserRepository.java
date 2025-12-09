package com.ecommerce.user.repository;

import com.ecommerce.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 🗄️ USER REPOSITORY - The Database Access Layer
 * 
 * Think of this as your "Database Assistant" - it handles all MongoDB operations.
 * 
 * What is MongoRepository?
 * It's a Spring Data interface that gives you FREE database methods:
 * - save() - Create or update
 * - findById() - Find by ID
 * - findAll() - Get all users
 * - deleteById() - Delete user
 * - count() - Count users
 * ...and many more!
 * 
 * You DON'T need to write SQL or MongoDB queries for basic operations!
 * 
 * Real-life analogy:
 * You're a manager, this is your secretary who handles all filing and retrieval.
 * You just say "Find user with email X" and they do the work!
 * 
 * Custom Query Methods:
 * Spring is SMART! If you name methods correctly, it auto-generates queries:
 * - findByEmail() → finds user where email = ?
 * - findByFirstName() → finds users where firstName = ?
 * - existsByEmail() → checks if email exists
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    
    /**
     * Find a user by email address
     * Spring automatically creates this query: db.users.findOne({email: "..."})
     */
    Optional<User> findByEmail(String email);
    
    /**
     * Check if an email already exists (for duplicate validation)
     * Returns true/false
     */
    boolean existsByEmail(String email);
    
    /**
     * Find all users by first name (case-insensitive)
     * MongoDB query: db.users.find({firstName: {$regex: "^name$", $options: 'i'}})
     */
    Optional<User> findByFirstNameIgnoreCase(String firstName);
}
