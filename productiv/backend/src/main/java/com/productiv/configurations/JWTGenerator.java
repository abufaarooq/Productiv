package com.productiv.configurations;



import java.util.Date;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTGenerator 
{

public static final long JWTExpiration = 6000;
public static final String JWT_SECRET = "secret";


public String generateToken (Authentication authentication) 
{

    String username = authentication.getName();
    Date currentDate = new Date();
    Date expireDate = new Date(currentDate.getTime() + JWTExpiration);

    String token = Jwts.builder()
    .setSubject(username)
    .setIssuedAt(currentDate)
    .setExpiration(expireDate)
    .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
    .compact();
    return token;
}

public String getUsername(String token) 
{
    Claims claims = Jwts.parser()
                    .setSigningKey(JWT_SECRET)
                    .parseClaimsJws(token)
                    .getBody();
                return claims.getSubject();
}
public boolean validateToken(String token)
{
    try 
    {
        Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
        return true;
    } catch (Exception e)
    {
        throw new AuthenticationCredentialsNotFoundException("JWT has expired or incorrect.");
    }
}

}