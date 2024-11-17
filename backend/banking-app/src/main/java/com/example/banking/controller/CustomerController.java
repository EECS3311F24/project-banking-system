package com.example.banking.controller;

import com.example.banking.dto.CustomerLoginRequest;
import com.example.banking.dto.CustomerRegistrationRequest;
import com.example.banking.dto.CustomerRequest;
import com.example.banking.dto.CustomerResponse;
import com.example.banking.model.Account; // Added for linking accounts
import com.example.banking.model.Customer;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.CustomerRepository;
import com.example.banking.service.CustomerService;
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
    private CustomerService customerService; // REPLACED DIRECT REPOSITORY USAGE WITH SERVICE LAYER

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody CustomerRegistrationRequest request) {
        try {
            // ADDED: Use service to validate email uniqueness
            customerService.validateEmailUniqueness(request.getEmail());

            // MOVED: Registration logic to service layer
            customerService.registerCustomer(request);

            return ResponseEntity.status(201).body("Registration successful");
        } catch (IllegalArgumentException e) {
            // MODIFIED: Handle custom exceptions for duplicate emails
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // ADDED: Generic exception handling
            return ResponseEntity.status(500).body("An unexpected error occurred");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CustomerLoginRequest request) {
        try {
            // MOVED: Login logic to service layer
            customerService.loginCustomer(request);

            return ResponseEntity.ok("Login successful");
        } catch (IllegalArgumentException e) {
            // MODIFIED: Handle login errors with meaningful messages
            return ResponseEntity.status(401).body(e.getMessage());
        } catch (Exception e) {
            // ADDED: Generic exception handling
            return ResponseEntity.status(500).body("An unexpected error occurred");
        }
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
