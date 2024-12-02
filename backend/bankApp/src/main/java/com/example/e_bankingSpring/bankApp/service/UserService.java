package com.example.e_bankingSpring.bankApp.service;

import com.example.e_bankingSpring.bankApp.entity.Transaction;
import com.example.e_bankingSpring.bankApp.entity.Users;
import com.example.e_bankingSpring.bankApp.repository.TransactionRepo;
import com.example.e_bankingSpring.bankApp.repository.UsersRepo;
import com.example.e_bankingSpring.bankApp.request.LoginRequest;
import com.example.e_bankingSpring.bankApp.request.TransactionRequest;
import com.example.e_bankingSpring.bankApp.request.TransferRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional
public class UserService {

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    private TransactionRepo transactionRepo;

    public Users addUser(Users user) {
        String accountNumber = generateAccountNumber();
        user.setAccountNumber(accountNumber);
        user.setBalance(0.0);
        return usersRepo.save(user);
    }

    private String generateAccountNumber() {
        String accountNumber = String.valueOf((long) (Math.random() * 1_000_000_0000L));

        // Ensure the account number is unique
        while (usersRepo.findByAccountNumber(accountNumber).isPresent()) {
            accountNumber = String.valueOf((long) (Math.random() * 1_000_000_0000L));
        }
        return accountNumber;
    }

    public Users login(LoginRequest loginRequest) throws IllegalArgumentException {
        Users user = usersRepo.findById(loginRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        return user;
    }
    public Users deposit(TransactionRequest transactionRequest) {
        Users user = (Users) usersRepo.findByAccountNumber(transactionRequest.getAccountNumber())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        user.setBalance(user.getBalance() + transactionRequest.getAmount());
        usersRepo.save(user);

        // Log the transaction
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(user.getAccountNumber());
        transaction.setType("Deposit");
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setDescription("Deposit to account");
        transactionRepo.save(transaction);

        return user;
    }

    public Users withdraw(TransactionRequest transactionRequest) {
        Users user = (Users) usersRepo.findByAccountNumber(transactionRequest.getAccountNumber())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        if (user.getBalance() < transactionRequest.getAmount()) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        user.setBalance(user.getBalance() - transactionRequest.getAmount());
        usersRepo.save(user);

        // Log the transaction
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(user.getAccountNumber());
        transaction.setType("Withdrawal");
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setDescription("Withdrawal from account");
        transactionRepo.save(transaction);

        return user;
    }

    public void transfer(TransferRequest transferRequest) {
        Users sender = usersRepo.findByEmail(transferRequest.getFromEmail())
                .orElseThrow(() -> new IllegalArgumentException("Sender account not found"));

        Users receiver = usersRepo.findByEmail(transferRequest.getToEmail())
                .orElseThrow(() -> new IllegalArgumentException("Receiver account not found"));

        if (sender.getBalance() < transferRequest.getAmount()) {
            throw new IllegalArgumentException("Insufficient funds in sender's account");
        }

        sender.setBalance(sender.getBalance() - transferRequest.getAmount());
        receiver.setBalance(receiver.getBalance() + transferRequest.getAmount());
        usersRepo.save(sender);
        usersRepo.save(receiver);

        // Log the transaction for sender
        Transaction senderTransaction = new Transaction();
        senderTransaction.setAccountNumber(sender.getAccountNumber());
        senderTransaction.setType("Transfer");
        senderTransaction.setAmount(-transferRequest.getAmount());
        senderTransaction.setTimestamp(LocalDateTime.now());
        senderTransaction.setDescription("Transferred to " + receiver.getName());
        transactionRepo.save(senderTransaction);

        // Log the transaction for receiver
        Transaction receiverTransaction = new Transaction();
        receiverTransaction.setAccountNumber(receiver.getAccountNumber());
        receiverTransaction.setType("Transfer");
        receiverTransaction.setAmount(transferRequest.getAmount());
        receiverTransaction.setTimestamp(LocalDateTime.now());
        receiverTransaction.setDescription("Received from " + sender.getName());
        transactionRepo.save(receiverTransaction);
    }

    public List<Transaction> getTransactionHistory(String accountNumber) {
        return transactionRepo.findByAccountNumberOrderByTimestampDesc(accountNumber);
    }

    public Users getUserByAccountNumber(String accountNumber) {
        return (Users) usersRepo.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
    }

    public Transaction getRecentTransaction(String accountNumber) {
        return transactionRepo.findFirstByAccountNumberOrderByTimestampDesc(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("No transactions found for account number: " + accountNumber));
    }
}