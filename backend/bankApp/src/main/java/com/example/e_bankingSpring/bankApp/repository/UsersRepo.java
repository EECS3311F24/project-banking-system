package com.example.e_bankingSpring.bankApp.repository;

import com.example.e_bankingSpring.bankApp.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<Users, String> {
    Optional<Users> findByEmail(String email);

    Optional<Object> findByAccountNumber(String accountNumber);
}
