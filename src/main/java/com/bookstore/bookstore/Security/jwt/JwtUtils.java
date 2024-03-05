package com.bookstore.bookstore.Security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.bookstore.bookstore.Security.CustomUserDetails;
import com.bookstore.bookstore.model.enums.TokenClaims;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String createToken(Map<String, Object> claims, String subject){
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + 100000);

        return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(subject)
                    .setIssuedAt(now)
                    .setExpiration(expirationDate)
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                    .compact();
    }

    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateJwtToken(CustomUserDetails customUserDetails) {
        Map<String, Object> claims = customUserDetails.getClaims();
        claims.put(TokenClaims.ID.getValue(), customUserDetails.getId());
        return createToken(claims, customUserDetails.getUsername());
    }

    public String generateJwtToken(Authentication auth) {
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        Map<String, Object> claims = userDetails.getClaims();
        return createToken(claims, userDetails.getUsername());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            //log.error("JwtUtils | validateJwtToken | Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            //log.error("JwtUtils | validateJwtToken | JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
           // log.error("JwtUtils | validateJwtToken | JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
           // log.error("JwtUtils | validateJwtToken | JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    public String getEmailFromToken(String token) {
        return extractClaims(token).get(TokenClaims.EMAIL.getValue()).toString();
    }

}
