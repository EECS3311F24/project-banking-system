package com.example.banking.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@Getter
@NoArgsConstructor

public class CustomerRegistrationRequest {

    private String name;
    private String email;
    private String password;

    // Getters and setters

}
