package com.arcyriea.usersecuritypractice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/admin/**").authenticated() //denyAll() would cause WhiteLabel page error with a 403, and that's normal.
                        .requestMatchers("/user/**").permitAll()
                        .anyRequest().authenticated() // Optionally add this to secure other endpoints by default
                )
                .formLogin(form -> {}
                        // You can configure form login details here if needed
                )
                .httpBasic(httpBasic -> {}
                        // You can configure HTTP Basic details here if needed
                );

        return http.build();
    }
}
