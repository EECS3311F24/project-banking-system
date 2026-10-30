package com.example.e_bankingSpring.bankApp.controller;

import com.example.e_bankingSpring.bankApp.entity.Account;
import com.example.e_bankingSpring.bankApp.request.BalanceRequest;
import com.example.e_bankingSpring.bankApp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/balance/{accountNumber}")
    public ResponseEntity<BalanceRequest> getBalance(@PathVariable String accountNumber) {
        try {
            Account account = accountService.getAccountDetails(accountNumber);
            BalanceRequest response = new BalanceRequest(account.getAccountNumber(), account.getBalance());
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
