package org.sebprojects.expensetracker.services;

import org.sebprojects.expensetracker.entities.UserInfo;
import org.sebprojects.expensetracker.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo>  userInfo = userRepository.findByUserName(username);
        if(userInfo.isPresent()) {
            return new CustomUserDetails(userInfo.get());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
