package com.aab.dev_connect.config;

import com.aab.dev_connect.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
public class SecurityConfig {

    UserService userService;

    public SecurityConfig(final UserService userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/projects").hasRole("PROJECT_OWNER") // Only ADMIN can create projects
                        .requestMatchers("/api/projects/*").hasRole("PROJECT_OWNER") // Only ADMIN can view projects
                        .requestMatchers("/api/tasks").hasRole("TASK_OWNER") // Only USER can create tasks
                        .requestMatchers("/api/tasks/*").hasRole("TASK_OWNER") // Only USER can view tasks
                        .anyRequest().authenticated())
                .formLogin(login -> login.permitAll())
                .logout(logout -> logout.permitAll())
                .userDetailsService(userService);
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
