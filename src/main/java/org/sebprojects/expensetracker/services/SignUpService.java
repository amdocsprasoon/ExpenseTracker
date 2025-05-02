package org.sebprojects.expensetracker.services;

import org.sebprojects.expensetracker.dtos.UserInfoDto;
import org.sebprojects.expensetracker.entities.UserInfo;
import org.sebprojects.expensetracker.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class SignUpService {

    // This service will handle the sign-up process for new users.
    // It will include methods to validate user input, save user data, and send confirmation emails.

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    // Example method to register a new user
    public void registerUser(UserInfoDto userInfoDto) {
        if( !userRepository.existsByUserName(userInfoDto.getUsername()))
        {
            throw new RuntimeException("User already exists");
        }

        UserInfo userInfo = UserInfo.builder()
                .userName(userInfoDto.getUsername())
                .password(passwordEncoder.encode(userInfoDto.getPassword()))
                .roles(new HashSet<>())  //no roles assigned yet
                .build();

        // Logic to save user data
        userRepository.save(userInfo);



    }

    // Example method to send confirmation email
    public void sendConfirmationEmail(String email) {
        // Logic to send confirmation email
    }


}
