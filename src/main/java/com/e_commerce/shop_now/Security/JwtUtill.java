package com.e_commerce.shop_now.Security;

import java.security.Key;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
@Component
public class JwtUtill {
 private String secretKey="mySecretKeyis1234567811223344556677";
 private long validityInMilliseconds=3600000; // 1 hour
 public Key getsigningKey() {
     return Keys.hmacShaKeyFor(secretKey.getBytes());
 }

public String generateToken(UserDetails ud) {
    
    return Jwts.builder().setSubject(ud.getUsername())
    .claim("role", ud.getAuthorities())
    .setIssuedAt(new Date())
    .signWith(getsigningKey(),SignatureAlgorithm.HS512)
    .setExpiration(new Date(System.currentTimeMillis() + validityInMilliseconds))
    .compact(); 
}
public String extractusername(String token){
    return Jwts.parserBuilder()
    .setSigningKey(getsigningKey())
    .build()
    .parseClaimsJws(token)
    .getBody()
    .getSubject();
}
public boolean validateToken(String token,UserDetails userDetails){
    String username=extractusername(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
}
public boolean isTokenExpired(String token){
    Date expiration=Jwts.parserBuilder()
    .setSigningKey(getsigningKey())
    .build()
    .parseClaimsJws(token)
    .getBody()
    .getExpiration();
    return expiration.before(new Date());
}
}

