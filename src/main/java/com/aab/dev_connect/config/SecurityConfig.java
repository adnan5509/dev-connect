package com.aab.dev_connect.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Autowired
    DataSource dataSource;

    @Bean
    public UserDetailsManager userDetailsManager() {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/projects").hasRole("PROJECT_OWNER") // Only ADMIN can create projects
                        .requestMatchers("/api/projects/*").hasRole("PROJECT_OWNER") // Only ADMIN can view projects
                        .requestMatchers("/api/tasks").hasAnyRole("TASK_OWNER", "PROJECT_OWNER") // Only USER can create tasks
                        .requestMatchers("/api/tasks/*").hasRole("TASK_OWNER") // Only USER can view tasks
                        .anyRequest().authenticated())
                .formLogin(login -> login.permitAll())
                .logout(logout -> logout.permitAll());
        return httpSecurity.build();
    }


}
