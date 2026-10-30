package com.example.e_bankingSpring.bankApp.controller;

import com.example.e_bankingSpring.bankApp.entity.Transaction;
import com.example.e_bankingSpring.bankApp.entity.Users;
import com.example.e_bankingSpring.bankApp.request.LoginRequest;
import com.example.e_bankingSpring.bankApp.request.TransactionRequest;
import com.example.e_bankingSpring.bankApp.request.TransferRequest;
import com.example.e_bankingSpring.bankApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UsersController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<Users> addUser(@RequestBody Users user) {
        try {
            Users savedUser = userService.addUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // Better error handling could include a custom error object
        }
    }
    @GetMapping("/account/balance/{accountNumber}")
    public ResponseEntity<Map<String, Object>> getAccountDetails(@PathVariable String accountNumber) {
        try {
            Users user = userService.getUserByAccountNumber(accountNumber);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("accountNumber", user.getAccountNumber());
            response.put("balance", user.getBalance());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Map<String, Object>> login( @RequestBody LoginRequest loginRequest) {
        try {
           Users user =  userService.login(loginRequest); // Assuming exceptions are thrown for invalid login
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("name", user.getName());
            response.put("accountNumber", user.getAccountNumber());
            response.put("balance", user.getBalance());
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An error occurred during login"));
        }
    }
    @PostMapping("/deposit")
    public ResponseEntity<Users> deposit(@RequestBody TransactionRequest transactionRequest) {
        try {
            Users updatedUser = userService.deposit(transactionRequest);
            return ResponseEntity.ok(updatedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Users> withdraw(@RequestBody TransactionRequest transactionRequest) {
        try {
            Users updatedUser = userService.withdraw(transactionRequest);
            return ResponseEntity.ok(updatedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransferRequest transferRequest) {
        try {
            userService.transfer(transferRequest);
            return ResponseEntity.ok("Transfer successful!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during the transfer");
        }
    }
    @GetMapping("/transactionHistory/{accountNumber}")
    public ResponseEntity<List<Transaction>> getTransactionHistory(@PathVariable String accountNumber) {
        try {
            List<Transaction> transactions = userService.getTransactionHistory(accountNumber);
            return ResponseEntity.ok(transactions);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/recentTransaction/{accountNumber}")
    public ResponseEntity<Transaction> getRecentTransaction(@PathVariable String accountNumber) {
        try {
            Transaction recentTransaction = userService.getRecentTransaction(accountNumber);
            return ResponseEntity.ok(recentTransaction);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Handle no transactions found
        }
    }


}
