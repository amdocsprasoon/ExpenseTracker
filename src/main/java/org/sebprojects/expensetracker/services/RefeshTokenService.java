package org.sebprojects.expensetracker.services;

// 1st - create Refrsh Token

import org.sebprojects.expensetracker.entities.RefreshToken;
import org.sebprojects.expensetracker.entities.UserInfo;
import org.sebprojects.expensetracker.repos.RefreshTokenRepository;
import org.sebprojects.expensetracker.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class RefeshTokenService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    public RefreshToken createRefreshToken (String userName) {
        UserInfo user = userRepository.findByUserName(userName).orElseThrow(() -> new RuntimeException("User Not Found"));

        return RefreshToken.builder()
                .userInfo(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(LocalDate.now().plusDays(30))
                .build();
    }

    public boolean verifyExpiration(RefreshToken refreshToken) {
         boolean verify = refreshToken.getExpiryDate().isBefore(LocalDate.now());
         if(!verify)
         {
             throw new RuntimeException("Token Expired, please Login in again");
         }
         return true;
    }



}

