package com.arcyriea.usersecuritypractice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import com.arcyriea.usersecuritypractice.security.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig{


    @Autowired
    private CustomUserDetailService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CorsFilter corsFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http    
                .csrf(customizer -> customizer.disable())
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/admin/**").authenticated() //denyAll() would cause WhiteLabel page error with a 403, and that's normal.
                        .requestMatchers("/user/**").permitAll()
                        .requestMatchers("/register", "/login").permitAll()
                        .anyRequest().authenticated() // Optionally add this to secure other endpoints by default
                )
                // .formLogin(form -> form
                //         .loginProcessingUrl("/login") 
                //         .usernameParameter("email")
                //         .passwordParameter("password")
                //         // You can configure form login details here if needed
                // )
                .httpBasic(Customizer.withDefaults()
                        // You can configure HTTP Basic details here if needed
                )
                .userDetailsService(userDetailsService); // Specify your UserDetailsService
                

        return http.build();
    }

//     @Bean
//     public UserDetailsService userDetailsService(){
        
//     }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder);
        authProvider.setUserDetailsService(userDetailsService);
        return authProvider;
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
