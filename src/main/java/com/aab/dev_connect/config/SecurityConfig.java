package com.aab.dev_connect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails projectOwner = User.withDefaultPasswordEncoder()
                .username("adnan")
                .password("adnan")
                .roles("PROJECT_OWNER")
                .build();

        UserDetails taskOwner = User.withDefaultPasswordEncoder()
                .username("kamran")
                .password("kamran")
                .roles("TASK_OWNER")
                .build();

        return new InMemoryUserDetailsManager(projectOwner, taskOwner);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/projects").hasRole("PROJECT_OWNER") // Only ADMIN can create projects
                        .requestMatchers("/api/projects/*").hasRole("PROJECT_OWNER") // Only ADMIN can view projects
                        .requestMatchers("/api/tasks").hasAnyRole("TASK_OWNER","PROJECT_OWNER") // Only USER can create tasks
                        .requestMatchers("/api/tasks/*").hasRole("TASK_OWNER") // Only USER can view tasks
                        .anyRequest().authenticated())
                .formLogin(login -> login.permitAll())
                .logout(logout -> logout.permitAll());
        return httpSecurity.build();
    }


}
