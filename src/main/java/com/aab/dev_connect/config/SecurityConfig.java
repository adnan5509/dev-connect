package com.aab.dev_connect.config;

import com.aab.dev_connect.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class SecurityConfig {

    UserService userService;

    public SecurityConfig(final UserService userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for stateless APIs
                .authorizeHttpRequests(auth -> auth
                        // Endpoints (STRICTER RULES FIRST)
                        .requestMatchers(HttpMethod.DELETE, "/api/tasks/*").hasRole("PROJECT_ADMIN") // Delete task
                        .requestMatchers(HttpMethod.POST, "/api/tasks").hasAnyRole("PROJECT_ADMIN") // Create task
                        .requestMatchers(HttpMethod.GET, "/api/tasks/*").hasAnyRole("TASK_ASSIGNEE", "PROJECT_ADMIN") // View task
                        .requestMatchers(HttpMethod.PUT, "/api/tasks/*").hasAnyRole("TASK_ASSIGNEE", "PROJECT_ADMIN") // Update task

                        .requestMatchers(HttpMethod.POST, "/api/projects").hasRole("PROJECT_ADMIN") // Create project
                        .requestMatchers(HttpMethod.PUT, "/api/projects/*").hasRole("PROJECT_ADMIN") // Update project
                        .requestMatchers(HttpMethod.DELETE, "/api/projects/*").hasRole("PROJECT_ADMIN") // Delete project
                        .requestMatchers(HttpMethod.GET, "/api/projects/*").hasRole("PROJECT_ADMIN") // View project
                        .requestMatchers(HttpMethod.POST, "/user/register").permitAll() // Register user

//                        .requestMatchers(HttpMethod.GET, "/api/projects/**").hasRole("PROJECT_ADMIN") // View task
//                        .requestMatchers(HttpMethod.GET, "/api/tasks/**").hasRole("TASK_ASSIGNEE") // View task

                        // All other requests require authentication (GENERAL RULE LAST)
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())  // Only if using form-based auth
                .logout(LogoutConfigurer::permitAll) // Only if using form-based auth
                .userDetailsService(userService); // Ensure this is properly configured

        return httpSecurity.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
