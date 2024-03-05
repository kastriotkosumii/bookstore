package com.bookstore.bookstore.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.bookstore.model.enums.Role;
import com.bookstore.bookstore.model.enums.TokenClaims;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
@Table(name = "users")
public class User {
    
    @Id
    @SequenceGenerator(
        name = "user_id_seq",
        sequenceName = "user_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "user_id_seq"
    )
    private Long id;

    @Column( nullable = false)
    private String fullName;

    @Column( nullable = false)
    private String username;

    @Column( nullable = false)
    private String email;

    @Column( nullable = false)
    private String password;

    @Column( nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public Map<String, Object> getClaims() {
        final Map<String, Object> claims = new HashMap<>();
        claims.put(TokenClaims.ID.getValue(), this.id);
        claims.put(TokenClaims.USERNAME.getValue(), this.username);
        claims.put(TokenClaims.ROLES.getValue(), List.of(this.role));
        claims.put(TokenClaims.USER_FULL_NAME.getValue(), this.fullName);
        claims.put(TokenClaims.EMAIL.getValue(), this.email);
        return claims;
    }

}
