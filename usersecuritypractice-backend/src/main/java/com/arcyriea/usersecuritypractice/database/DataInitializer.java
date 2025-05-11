package com.arcyriea.usersecuritypractice.database;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    
    @Override
    public void run(String... args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
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
