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
public class UserLoginResponse {

    private String username;

    private String email;

    private String bio;
    private String githubUrl;
    private List<AuthorityResponse> authorities;
    private String token;

}
