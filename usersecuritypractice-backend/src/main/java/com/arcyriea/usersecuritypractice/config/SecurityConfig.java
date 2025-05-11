package com.arcyriea.usersecuritypractice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.arcyriea.usersecuritypractice.security.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig{


    @Autowired
    private CustomUserDetailService userDetailService;


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
                )
                .userDetailsService(userDetailService); // Specify your UserDetailsService


        return http.build();
    }

//     @Bean
//     public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//         UserDetails bushan = User.builder()
//                 .username("bushan")
//                 .password(passwordEncoder.encode("12345"))  // Use a proper encoder!
//                 .roles("ADMIN")
//                 .build();
//         UserDetails pavan = User.builder()
//                 .username("pavan")
//                 .password(passwordEncoder.encode("12345"))  // Use a proper encoder!
//                 .roles("USER")
//                 .build();
//         return new InMemoryUserDetailsManager(bushan, pavan);
//     } // for inmemory user creation

    
    
}
