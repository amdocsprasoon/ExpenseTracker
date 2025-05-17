package org.sebprojects.expensetracker.services;

import org.sebprojects.expensetracker.dtos.UserInfoDto;
import org.sebprojects.expensetracker.entities.Roles;
import org.sebprojects.expensetracker.entities.UserInfo;
import org.sebprojects.expensetracker.kafka.UserInfoDtoProducer;
import org.sebprojects.expensetracker.repos.RolesRepository;
import org.sebprojects.expensetracker.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class SignUpService {

    // This service will handle the sign-up process for new users.
    // It will include methods to validate user input, save user data, and send confirmation emails.

    @Autowired
    UserRepository userRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RefeshTokenService refeshTokenService;

    @Autowired
    UserInfoDtoProducer userInfoDtoProducer;

    // Example method to register a new user
    public UserInfoDto registerUser(UserInfoDto userInfoDto) {
        if(userRepository.existsByUserName(userInfoDto.getUsername()))
        {
            throw new RuntimeException("User already exists");
        }

        // Fetch the ROLE_USER from the database
        Optional<Roles> roleUser = rolesRepository.findByRoleName("ROLE_USER");
        if (roleUser.isEmpty()) {
            throw new RuntimeException("Default role ROLE_USER not found in the database");
        }

        // Assign ROLE_USER to the new user
        Set<Roles> roles = new HashSet<>();
        System.out.println("Role User: " + roleUser.get());
        roles.add(roleUser.get());

        System.out.println(roles);

        UserInfo userInfo = UserInfo.builder()
                .userName(userInfoDto.getUsername())
                .password(passwordEncoder.encode(userInfoDto.getPassword()))
                .roles(roles)  //no roles assigned yet
                .build();

        // Logic to save user data
        UserInfo userInfo1 =  userRepository.save(userInfo);

        // Logic to send kafka message
         userInfoDtoProducer.sendUserInfoDtoEvent("user_info_topic", userInfoDto);

        return UserInfoDto.builder()
                .username(userInfo1.getUserName())
                .password("Can't be shown")
                .roles(userInfo1.getRoles())
                .build();

    }

    public boolean isUserNameAvailable(String userName) {
        return userRepository.existsByUserName(userName);
    }



    // Example method to send confirmation email
    public void sendConfirmationEmail(String email) {
        // Logic to send confirmation email
    }


}
