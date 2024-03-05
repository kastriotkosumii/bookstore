package com.bookstore.bookstore.service;

import com.bookstore.bookstore.payload.request.auth.LoginRequest;
import com.bookstore.bookstore.payload.request.auth.SignupRequest;
import com.bookstore.bookstore.payload.response.auth.JWTResponse;

public interface AuthService {
    String register(SignupRequest request);

    JWTResponse login(LoginRequest request);
}
