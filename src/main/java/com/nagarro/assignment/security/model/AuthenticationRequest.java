package com.nagarro.assignment.security.model;

import lombok.*;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}
