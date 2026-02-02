package com.example.student.Security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.student.Entity.User;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class AuthUtil {
    
    // @Value("${jwt.secrectKey}")
    // private String jwtSecrectKey;

    // private SecretKey getSecretKey(){
    //     return Keys.hmacShaKeyFor(jwtSecrectKey.getBytes(StandardCharsets.UTF_8));
    // }

    // public String generateJwtToken(User user){
    //     return Jwts.builder()
    //                 .subject(user.getUsername())
    //                 .claim("UserId", user.getId().toString())
    //                 .issuedAt(new Date())
    //                 .expiration(new Date(System.currentTimeMillis() + 1000*60*10))
    //                 .signWith(getSecretKey())
    //                 .compact();
    // }

    private String jwtSecretKey;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(
            jwtSecretKey.getBytes(StandardCharsets.UTF_8)
        );
    }

    public String generateJwtToken(User user) {

        return Jwts.builder()
                .subject(user.getUsername())
                .claim("userId", user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSecretKey())
                .compact();
    }


}
