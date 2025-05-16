package com.arcyriea.usersecuritypractice.database;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.arcyriea.usersecuritypractice.entity.Account;
import com.arcyriea.usersecuritypractice.repository.AccountRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class DataInitializer {

    private static final String INITIALIZED_FILE = "application_initialized.lock";
    @Autowired
    private AccountRepository userRepository;
    // private final JdbcTemplate jdbcTemplate;
    @Autowired
    private PasswordEncoder encoder;
    
    @PostConstruct
    public void initialize(){
        File initializedFile = new File(INITIALIZED_FILE);
        if (!initializedFile.exists()){
            try {
            System.out.println("Initializing data... Adding Preconfigured Users");

            Account user1 = new Account();
            user1.setEmail("Artist001@art.com");
            user1.setPassword(encoder.encode("88888888"));
            System.out.println(user1.getEmail());


            Account user2 = new Account();
            user2.setEmail("Artist002@art.com");
            user2.setPassword(encoder.encode("88888888"));
            System.out.println(user2.getEmail());

            userRepository.save(user1);
            userRepository.save(user2);
            initializedFile.createNewFile();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.out.println("Application's Database data already initialized");
        }
    }
}
