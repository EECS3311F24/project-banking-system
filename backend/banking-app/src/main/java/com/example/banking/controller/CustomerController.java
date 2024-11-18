package com.example.banking.controller;

import com.example.banking.dto.CustomerRequest;
import com.example.banking.dto.CustomerResponse;
import com.example.banking.model.Account; // Added for linking accounts
import com.example.banking.model.Customer;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

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
