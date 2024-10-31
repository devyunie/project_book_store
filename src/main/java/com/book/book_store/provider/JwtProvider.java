package com.book.book_store.provider;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {
    
    @Value("${jwt.secret}")
    private String secretKey;

    public String create(String userId){
        Date expireDate =Date.from(Instant.now().plus(12,ChronoUnit.HOURS));

        String jwt = null;
        
        try {
            Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

            jwt = Jwts.builder()
                    .signWith(key,SignatureAlgorithm.HS256)
                    .setSubject(userId)
                    .setIssuedAt(new Date())
                    .setExpiration(expireDate)
                    .compact();

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        return jwt;
    }

    public String validate(String jwt){
        String userId = null;
        try {
            Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
            
            userId = Jwts.parserBuilder()
                        .build()
                        .parseClaimsJws(jwt)
                        .getBody()
                        .getSubject();

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        return userId;
    }
}
