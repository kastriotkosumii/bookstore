package com.bookstore.bookstore.service;

import com.bookstore.bookstore.model.User;

public interface RefreshTokenService {

    String createRefreshToken(User user);
    
}
