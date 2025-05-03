package org.sebprojects.expensetracker.controllers;

import org.sebprojects.expensetracker.dtos.UserInfoDto;
import org.sebprojects.expensetracker.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TokenController {

    // This controller will handle the token generation and validation for JWT
    // It will also handle the refresh token functionality
    // The controller will be secured with Spring Security
    // The controller will use the JWT library to generate and validate tokens
    // The controller will use the UserDetailsService to load user details from the database
    // The controller will use the PasswordEncoder to encode and verify passwords
    // The controller will use the AuthenticationManager to authenticate users
    // The controller will use the JwtTokenProvider to generate and validate tokens

    // The controller will use the JwtAuthenticationFilter to filter requests and validate tokens
    // The controller will use the JwtAuthenticationEntryPoint to handle authentication errors
    // The controller will use the JwtTokenProvider to generate and validate tokens

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserInfoDto userInfoDto) {
        // This method will handle the login functionality
        // It will generate a JWT token and return it to the client
        try {
            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userInfoDto.getUsername(), userInfoDto.getPassword())
            );

            // Generate JWT token if authentication is successful
            String jwtToken = jwtService.generateToken(authentication.getName());

            return ResponseEntity.ok("Login successful. JWT Token: " + jwtToken);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

}
