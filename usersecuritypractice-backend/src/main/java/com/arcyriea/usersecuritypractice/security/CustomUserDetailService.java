package com.arcyriea.usersecuritypractice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.arcyriea.usersecuritypractice.entity.Account;
import com.arcyriea.usersecuritypractice.repository.AccountRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private AccountRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User of email "+username+" could not be found!"));

        return new UserPrincipals(user);
    }

    public void registerUser(String username, String password){
        String encodedPassword = encoder.encode(password);
        Account user = new Account(username, encodedPassword);
        userRepository.save(user);
    }
    
}
