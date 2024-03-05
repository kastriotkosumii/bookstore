package com.bookstore.bookstore.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bookstore.bookstore.model.User;
import com.bookstore.bookstore.repository.UserRepository;
import com.bookstore.bookstore.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> fundById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
}
