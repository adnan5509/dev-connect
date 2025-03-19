package com.aab.dev_connect.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequest {

    private String username;

    private String password;

    private String email;

    private String bio;
    private String githubUrl;

    private List<AuthorityRequest> authorities;
}
