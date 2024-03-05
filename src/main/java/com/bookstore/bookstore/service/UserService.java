package com.bookstore.bookstore.service;

import java.util.Optional;

import com.bookstore.bookstore.model.User;

public interface UserService {
    Optional<User> findByUsername(String username);
    Optional<User> fundById(Long id);
    Optional<User> findByEmail(String email);
}
