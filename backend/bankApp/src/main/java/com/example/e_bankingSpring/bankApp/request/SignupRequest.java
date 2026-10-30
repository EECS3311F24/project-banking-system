package com.example.e_bankingSpring.bankApp.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SignupRequest {
    private String name;
    private String email;
    private String password;
    private String existingEmail; // For linking a new account
}
