package com.bookstore.bookstore.Security.jwt;
import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bookstore.bookstore.Security.CustomUserDetailsService;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter{

    private final JwtUtils jwtUtils;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain)
            throws ServletException, IOException {
       try{

        String jwt = parseJwt(request);

        if(jwt != null && jwtUtils.validateToken(jwt)){
            String email = jwtUtils.getEmailFromToken(jwt);
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

       }catch(Exception e){

       }
       
       filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request){
        String headerAuth = request.getHeader("Authentication");

        if(StringUtils.isNotEmpty(headerAuth) && headerAuth.startsWith("Bearer ")){
            return headerAuth.substring(7, headerAuth.length());
        }

        return null;
    }
    
}
