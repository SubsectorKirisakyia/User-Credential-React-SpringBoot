package com.arcyriea.usersecuritypractice.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import com.arcyriea.usersecuritypractice.entity.Account;
import com.arcyriea.usersecuritypractice.repository.AccountRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private AccountRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User of email "+username+" could not be found!"));

        return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
    
}
