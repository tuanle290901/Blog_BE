package com.example.blogbe.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
