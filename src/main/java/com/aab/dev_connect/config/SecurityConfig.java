package com.aab.dev_connect.config;

import com.aab.dev_connect.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
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
                        // Project Endpoints
                        .requestMatchers(HttpMethod.POST, "/api/projects").hasRole("PROJECT_ADMIN") // Create project
                        .requestMatchers(HttpMethod.GET, "/api/projects/*").hasAnyRole("PROJECT_ADMIN", "TASK_ASSIGNEE") // View project
                        .requestMatchers(HttpMethod.PUT, "/api/projects/*").hasRole("PROJECT_ADMIN") // Update project
                        .requestMatchers(HttpMethod.DELETE, "/api/projects/*").hasRole("PROJECT_ADMIN") // Delete project

                        // Task Endpoints
                        .requestMatchers(HttpMethod.POST, "/api/tasks").hasAnyRole("PROJECT_ADMIN", "TASK_ASSIGNEE") // Create task
                        .requestMatchers(HttpMethod.GET, "/api/tasks/*").hasAnyRole("PROJECT_ADMIN", "TASK_ASSIGNEE") // View task
                        .requestMatchers(HttpMethod.PUT, "/api/tasks/*").hasRole("TASK_ASSIGNEE") // Update task
                        .requestMatchers(HttpMethod.DELETE, "/api/tasks/*").hasRole("TASK_ASSIGNEE") // Delete task

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
