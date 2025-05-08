package com.aab.dev_connect.contoller;

import com.aab.dev_connect.dto.AuthorityResponse;
import com.aab.dev_connect.dto.JwtResponse;
import com.aab.dev_connect.dto.LoginRequest;
import com.aab.dev_connect.dto.UserLoginResponse;
import com.aab.dev_connect.dto.UserRegistrationRequest;
import com.aab.dev_connect.dto.UserRegistrationResponse;
import com.aab.dev_connect.model.User;
import com.aab.dev_connect.service.JwtService;
import com.aab.dev_connect.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserController(final UserService userService, final AuthenticationManager authenticationManager, final JwtService jwtService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (Exception e) {
            // Handle authentication failure
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        // If authentication is successful, load the user details
        User user = userService.loadUserByUsername(request.getEmail());
        if (user == null) {
            // Handle user not found
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }else {
            // Generate JWT token
            String token = jwtService.generateToken(user);

            UserLoginResponse userLoginResponse = new UserLoginResponse();

            userLoginResponse.setUsername(user.getUsername());
            userLoginResponse.setEmail(user.getEmail());
            userLoginResponse.setBio(user.getBio());
            userLoginResponse.setGithubUrl(user.getGithubUrl());
            userLoginResponse.setAuthorities(user.getAuthorities().stream()
                    .map(authority -> new AuthorityResponse(authority.getAuthority())).collect(Collectors.toList()));
            userLoginResponse.setToken(token);
            return ResponseEntity.ok(userLoginResponse);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<UserRegistrationResponse> registerNewUser(@RequestBody UserRegistrationRequest userRegisterRequest) {
        UserRegistrationResponse userRegistrationResponse = userService.registerNewUser(userRegisterRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRegistrationResponse);
    }
}
