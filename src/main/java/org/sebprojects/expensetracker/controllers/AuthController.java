package org.sebprojects.expensetracker.controllers;

import org.sebprojects.expensetracker.dtos.UserInfoDto;
import org.sebprojects.expensetracker.entities.RefreshToken;
import org.sebprojects.expensetracker.services.JwtService;
import org.sebprojects.expensetracker.services.RefeshTokenService;
import org.sebprojects.expensetracker.services.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private RefeshTokenService refeshTokenService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserInfoDto userInfoDto){
        // Validate user input
        if(signUpService.isUserNameAvailable(userInfoDto.getUsername()))
            return ResponseEntity.badRequest().body("Username already exists");

        UserInfoDto savedUserInfoDto;
        // Save user data in UserInfo Repo
        try {
             savedUserInfoDto =  signUpService.registerUser(userInfoDto);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error saving user data: " + e.getMessage());
        }


        // Create a Refresh Token and save in the RefreshToken Repo
        RefreshToken refreshToken = refeshTokenService.createRefreshToken(userInfoDto.getUsername());

        // Create a JWT Token
        String jwtToken = jwtService.generateToken(userInfoDto.getUsername());



        return ResponseEntity.ok("User registered successfully with JWT: " + jwtToken + " and Refresh Token: " + refreshToken.getToken()
        + " and expiry date: " + refreshToken.getExpiryDate() + " and user info: " + savedUserInfoDto);
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello, World!";
    }

    @GetMapping("/hello2")
    public String helloWorld2() {
        return "Hello, World 2!";
    }


}
