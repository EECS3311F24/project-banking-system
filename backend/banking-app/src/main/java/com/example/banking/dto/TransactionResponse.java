package com.example.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;



@Getter
@AllArgsConstructor
@Setter

public class TransactionResponse {
    private Long id;
    private String type; // "DEPOSIT", "WITHDRAWAL", "TRANSFER"
    private Double amount;
    private String description;
    private LocalDateTime timestamp;

}
