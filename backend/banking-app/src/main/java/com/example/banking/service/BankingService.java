package com.example.banking.service;

import com.example.banking.model.Account;
import com.example.banking.model.Transaction;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankingService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public double deposit(String accountNumber, double amount) {
        // Fetch existing account
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        // Update balance
        account.setBalance(account.getBalance() + amount);

        // Save transaction
        Transaction transaction = new Transaction();
        transaction.setType("DEPOSIT");
        transaction.setAmount(amount);
        transaction.setAccount(account);
        transaction.setDescription("Deposit of " + amount);

        // Save changes
        accountRepository.save(account);
        transactionRepository.save(transaction);

        return account.getBalance(); // Return updated balance
    }

    public double withdraw(String accountNumber, double amount) {
        // Fetch existing account
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        // Check if the account has sufficient balance
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        // Update the account balance
        account.setBalance(account.getBalance() - amount);

        // Create a new transaction for the withdrawal
        Transaction transaction = new Transaction();
        transaction.setType("WITHDRAWAL");
        transaction.setAmount(amount);
        transaction.setAccount(account); // Link the transaction to the account
        transaction.setDescription("Withdrawal of " + amount);

        // Save the updated account and transaction
        accountRepository.save(account); // This will not create a new account ID
        transactionRepository.save(transaction); // Save the transaction with a new unique ID

        // Return the updated account balance
        return account.getBalance();
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber)
                .orElseThrow(() -> new RuntimeException("Source account not found"));
        Account toAccount = accountRepository.findByAccountNumber(toAccountNumber)
                .orElseThrow(() -> new RuntimeException("Destination account not found"));

        if (fromAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        Transaction transaction = new Transaction();
        transaction.setType("TRANSFER");
        transaction.setAmount(amount);
        transaction.setAccount(fromAccount);
        transaction.setDescription("Transfer to account " + toAccountNumber);

        Transaction recipientTransaction = new Transaction();
        recipientTransaction.setType("TRANSFER");
        recipientTransaction.setAmount(amount);
        recipientTransaction.setAccount(toAccount);
        recipientTransaction.setDescription("Transfer from account " + fromAccountNumber);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
        transactionRepository.save(transaction);
        transactionRepository.save(recipientTransaction);
    }

    public List<Transaction> getTransactionHistory(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return transactionRepository.findByAccount(account);
    }
}
