package com.aab.dev_connect.config;

import com.aab.dev_connect.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
public class SecurityConfig {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(final UserService userService, final PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable()) // Disable CSRF for stateless APIs
                .authorizeHttpRequests(auth -> auth
                        // Task Endpoints (STRICTER RULES FIRST)
                        .requestMatchers(HttpMethod.GET, "/api/tasks/*").hasRole("ROLE_TASK_ASSIGNEE") // View task
                        .requestMatchers(HttpMethod.PUT, "/api/tasks/*").hasRole("TASK_ASSIGNEE") // Update task
                        .requestMatchers(HttpMethod.DELETE, "/api/tasks/*").hasRole("TASK_ASSIGNEE") // Delete task
                        .requestMatchers(HttpMethod.POST, "/api/tasks").hasAnyRole("PROJECT_ADMIN", "TASK_ASSIGNEE") // Create task

                        // Project Endpoints
                        .requestMatchers(HttpMethod.POST, "/api/projects").hasRole("PROJECT_ADMIN") // Create project
                        .requestMatchers(HttpMethod.PUT, "/api/projects/*").hasRole("PROJECT_ADMIN") // Update project
                        .requestMatchers(HttpMethod.DELETE, "/api/projects/*").hasRole("PROJECT_ADMIN") // Delete project
                        .requestMatchers(HttpMethod.GET, "/api/projects/*").hasAnyRole("PROJECT_ADMIN", "TASK_ASSIGNEE") // View project

                        // All other requests require authentication (GENERAL RULE LAST)
                        .anyRequest().authenticated())
                .formLogin(login -> login.permitAll()) // Only if using form-based auth
                .logout(logout -> logout.permitAll()) // Only if using form-based auth
                .userDetailsService(userService); // Ensure this is properly configured

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
