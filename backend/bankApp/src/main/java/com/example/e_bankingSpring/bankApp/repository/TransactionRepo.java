package com.example.e_bankingSpring.bankApp.repository;


import com.example.e_bankingSpring.bankApp.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountNumberOrderByTimestampDesc(String accountNumber);

    Optional<Transaction> findFirstByAccountNumberOrderByTimestampDesc(String accountNumber);


}

