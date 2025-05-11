package com.arcyriea.usersecuritypractice.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    private PasswordEncoder encoder;
    
    @Override
    public void run(String... args) throws Exception {
        try {
            String password = "88888888";
            jdbcTemplate.execute("INSERT INTO users (email, password)\r\n" + //
                        "SELECT 'Artist001@art.com', '" + encoder.encode(password) + "'\r\n" + //
                        "WHERE NOT EXISTS (SELECT 1\r\n" + //
                        "    FROM users\r\n" + //
                        "    WHERE email = 'Artist001@art.com');");
        } catch (Exception e) {
           System.err.println(e.getMessage());
        }
        
    }
    
}
