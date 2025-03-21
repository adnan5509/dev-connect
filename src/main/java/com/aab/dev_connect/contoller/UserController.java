package com.aab.dev_connect.contoller;

import com.aab.dev_connect.dto.UserRegistrationRequest;
import com.aab.dev_connect.dto.UserRegistrationResponse;
import com.aab.dev_connect.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponse> registerNewUser(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        UserRegistrationResponse userRegistrationResponse = userService.registerNewUser(userRegistrationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRegistrationResponse);
    }
}
