package com.example.banking.controller;

import com.example.banking.dto.CustomerLoginRequest;
import com.example.banking.dto.CustomerRegistrationRequest;
import com.example.banking.dto.CustomerRequest;
import com.example.banking.dto.CustomerResponse;
import com.example.banking.model.Account; // Added for linking accounts
import com.example.banking.model.Customer;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody CustomerRegistrationRequest request) {
        // Check if the email already exists
        if (customerRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email is already registered");
        }

        // Create a new customer
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPassword(passwordEncoder.encode(request.getPassword())); // Encrypt the password

        customerRepository.save(customer);

        return ResponseEntity.status(201).body("Registration successful");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CustomerLoginRequest request) {
        // Find the customer by email
        Customer customer = customerRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        // Verify the password
        if (!passwordEncoder.matches(request.getPassword(), customer.getPassword())) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }

        return ResponseEntity.ok("Login successful");
    }



    
    @Autowired
    private AccountRepository accountRepository;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomerWithAccount(@RequestBody CustomerRequest request) {
        // Create customer
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());

        // Create account
        Account account = new Account();
        account.setAccountNumber(generateUniqueAccountNumber());
        account.setBalance(0.0);

        // Link account with customer
        customer.addAccount(account);

        // Save customer (cascades to account because of CascadeType.ALL)
        Customer savedCustomer = customerRepository.save(customer);

        return ResponseEntity.status(201).body(toCustomerResponse(savedCustomer));
    }

    private String generateUniqueAccountNumber() {
        return String.valueOf(System.currentTimeMillis()); // Example unique number generation
    }

    private CustomerResponse toCustomerResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getAccounts().stream().map(Account::getAccountNumber).collect(Collectors.toList())
        );
    }
}
