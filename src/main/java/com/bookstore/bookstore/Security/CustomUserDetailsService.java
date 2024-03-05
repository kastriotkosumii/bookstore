package com.bookstore.bookstore.Security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bookstore.bookstore.model.User;
import com.bookstore.bookstore.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userService.findByEmail(username)
                    .orElseThrow(() -> new Exception("User Name " + username + " not found"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new CustomUserDetails(user);
    }
    
}
