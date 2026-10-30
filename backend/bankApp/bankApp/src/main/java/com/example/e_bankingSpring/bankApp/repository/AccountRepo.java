package com.example.e_bankingSpring.bankApp.repository;

import com.example.e_bankingSpring.bankApp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, String> {
    Optional<Account> findByAccountNumber(String accountNumber);

}
