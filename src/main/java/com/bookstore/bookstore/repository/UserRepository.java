package com.bookstore.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.bookstore.model.User;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
