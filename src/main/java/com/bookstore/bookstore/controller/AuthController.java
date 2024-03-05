package com.bookstore.bookstore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.bookstore.payload.request.auth.LoginRequest;
import com.bookstore.bookstore.payload.request.auth.SignupRequest;
import com.bookstore.bookstore.payload.response.CustomResponse;
import com.bookstore.bookstore.payload.response.auth.JWTResponse;
import com.bookstore.bookstore.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<String> register(@RequestBody SignupRequest request) {

        return CustomResponse.created(authService.register(request));
    }

    @PostMapping("/login")
    public CustomResponse<JWTResponse> login(@RequestBody LoginRequest request) {

        return CustomResponse.ok(authService.login(request));
    }
    
}
