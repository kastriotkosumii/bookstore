package com.bookstore.bookstore.payload.request.auth;

import com.bookstore.bookstore.model.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupRequest {
    
    private String fullName;

    private String password;

    private String username;

    private String email;

    private Role role;
}
