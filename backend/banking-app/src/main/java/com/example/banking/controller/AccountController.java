package com.example.banking.controller;

import com.example.banking.dto.*;
import com.example.banking.model.Account;
import com.example.banking.model.Transaction;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.TransactionRepository;
import com.example.banking.service.BankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private BankingService bankingService;

    @PostMapping("/deposit")
    public ResponseEntity<OperationResponse> deposit(@RequestBody DepositRequest request) {
        double updatedBalance = bankingService.deposit(request.getAccountNumber(), request.getAmount());
        return ResponseEntity.ok(new OperationResponse("Deposit successful", updatedBalance));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<OperationResponse> withdraw(@RequestBody WithdrawRequest request) {
        double updatedBalance = bankingService.withdraw(request.getAccountNumber(), request.getAmount());
        return ResponseEntity.ok(new OperationResponse("Withdrawal successful", updatedBalance));
    }

    @PostMapping("/transfer")
    public ResponseEntity<OperationResponse> transfer(@RequestBody TransferRequest request) {
        bankingService.transfer(request.getFromAccountNumber(), request.getToAccountNumber(), request.getAmount());
        return ResponseEntity.ok(new OperationResponse("Transfer successful", request.getAmount()));
    }

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/{accountNumber}/transactions")
    public ResponseEntity<List<TransactionResponse>> getTransactionHistory(@PathVariable String accountNumber) {
        // Find the account by account number
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        // Fetch the transactions associated with the account
        List<Transaction> transactions = transactionRepository.findByAccount(account);

        // Map transactions to response DTOs
        List<TransactionResponse> response = transactions.stream()
                .map(transaction -> new TransactionResponse(
                        transaction.getId(),
                        transaction.getType(),
                        transaction.getAmount(),
                        transaction.getDescription(),
                        transaction.getTimestamp()
                ))
                .toList();

        return ResponseEntity.ok(response);
    }
}
