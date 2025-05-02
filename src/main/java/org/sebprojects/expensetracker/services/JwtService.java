package org.sebprojects.expensetracker.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {


    private static final String SECRET_KEY = "your-256-bit-secret-your-256-bit-secret"; // Use a secure key
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours


    // In this we are getting the signing key generated from the SECRET_KEY, if the SECRET_KEY is same, it will generate same signing key,
    // so we can use the same signing key to validate the JWT token
    // The signing key is used to sign the JWT token, and it is also used to validate the JWT token
    // The signing key is generated using the HMAC SHA algorithm
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Generate a JWT Token
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

//    private String createToken(Map<String, Object> claims, String subject) {
//
//    }

    // Below are the steps to validate the JWT token

    // Step 1 - It will extract all the claims by verifying using Signing Key
    // I think if it is not able to verify the signing key, it will throw an exception
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Step 2

    // Extract a Specific Claim, it's a generic class that is helpful to extract the claim that you want
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    // Extract Username from Token
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }

//        private Date extractExpiration(String token) {
//            return extractClaim(token, Claims::getExpiration);
//        }


    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }
    private Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }
    // Check if Token is Expired, compare it with today's date time
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    // Validate a JWT Token
    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}
