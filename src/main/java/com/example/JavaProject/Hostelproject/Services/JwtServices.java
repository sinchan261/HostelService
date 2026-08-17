package com.example.JavaProject.Hostelproject.Services;


import com.example.JavaProject.Hostelproject.Entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtServices {

    @Value("${JWT.SECRET_KEY}")
   private  String  key;

    private SecretKey generateKey(){
        return Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessKey(UserEntity user){
        String id = UUID.randomUUID().toString();
      return  Jwts.builder().id(id).subject(user.getId().toString())
                .claim("email",user.getEmail())
                .claim("password",user.getPassword())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000L*60*24)).signWith(generateKey()).compact();

    }

    public String generateRefreshToken(UserEntity userEntity){
        String id = UUID.randomUUID().toString();
       return Jwts.builder().id(id).subject(userEntity.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000L*60*24*30*6)).signWith(generateKey()).compact();
    }

    public Long getUserIdvalueFromToken(String token){
        Claims claim = Jwts.parser().verifyWith(this.generateKey()).build().parseSignedClaims(token).getPayload();
        return Long.valueOf(claim.getSubject());
    }
    public String getJtiFromToken(String token){
        Claims claim = Jwts.parser().verifyWith(this.generateKey()).build().parseSignedClaims(token).getPayload();
        return claim.getId();
    }
}
