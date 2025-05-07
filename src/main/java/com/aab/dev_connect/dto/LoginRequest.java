package com.aab.dev_connect.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
