package com.example.bank_app_spring.service;

import com.example.bank_app_spring.model.Customer;
import com.example.bank_app_spring.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer registerCustomer(String name, String email, String password) {
        if (customerRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email is already registered!");
        }

        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setAccountNumber(generateAccountNumber());

        return customerRepository.save(customer);
    }

    public Optional<Customer> login(String email, String password) {
        return customerRepository.findByEmail(email)
                .filter(customer -> customer.getPassword().equals(password));
    }

    private String generateAccountNumber() {
        return "AC" + UUID.randomUUID().toString().substring(0, 10).toUpperCase();
    }
}
