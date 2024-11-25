package com.example.bank_app_spring.controller;

import com.example.bank_app_spring.model.Customer;
import com.example.bank_app_spring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestParam String name,
                                              @RequestParam String email,
                                              @RequestParam String password) {
        try {
            Customer customer = customerService.registerCustomer(name, email, password);
            return ResponseEntity.ok(customer);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginCustomer(@RequestParam String email,
                                                @RequestParam String password) {
        return customerService.login(email, password)
                .<ResponseEntity<Object>>map(customer -> ResponseEntity.ok(customer))
                .orElse(ResponseEntity.status(401).body("Invalid email or password"));
    }
}