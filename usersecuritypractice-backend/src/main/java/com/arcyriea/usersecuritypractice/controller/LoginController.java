package com.arcyriea.usersecuritypractice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arcyriea.usersecuritypractice.entity.Account;
import com.arcyriea.usersecuritypractice.model.UserModel;
import com.arcyriea.usersecuritypractice.repository.AccountRepository;
import com.arcyriea.usersecuritypractice.security.JWTService;

@RestController
public class LoginController {
    @Autowired
    private AccountRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerAccount(@RequestBody UserModel userModel) {
        try {
            Account user = new Account();
            user.setEmail(userModel.getEmail());
            user.setPassword(encoder.encode(userModel.getPassword()));
            Account savedUser = userRepository.save(user);
            return new ResponseEntity<Object>(savedUser, HttpStatus.CREATED);
        } catch (Exception e){
            System.err.println("Error in creating credentials: " +e.getMessage());
            String exceptionMessage = "User creation failed: " + e.getMessage();
            return new ResponseEntity<Object>(exceptionMessage, HttpStatus.NOT_ACCEPTABLE);
        }
        
        
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserModel userModel) throws Exception{
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userModel.getEmail(), userModel.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            if (authentication.isAuthenticated()){
                String key = jwtService.generateToken(userModel.getEmail());
                return new ResponseEntity<String>("Login Successful: "+key, HttpStatus.OK);
            }
        } catch (BadCredentialsException e) {
            return new ResponseEntity<String>("Incorrect Username or Password", HttpStatus.UNAUTHORIZED);
        } catch (Exception e){
            return new ResponseEntity<String>("Technical problem with login method, please try again at another time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>("Login not resolved", HttpStatus.NOT_IMPLEMENTED); 
    }
}
