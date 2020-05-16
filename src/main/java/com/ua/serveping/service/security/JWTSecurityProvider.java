package com.ua.serveping.service.security;

import com.auth0.jwt.JWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Component
public class JWTSecurityProvider {

    @Value("${jwt.secret}")
    private String SECRET;

    public String doGenerateToken(User user) {

        String[] roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toArray(String[]::new);

        return JWT.create()
                .withClaim("emailId", user.getUsername())
                .withArrayClaim("roles", roles)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + (60 * 60 * 1000))) // 1 hour
                .sign(HMAC512(SECRET.getBytes()));
    }

}
