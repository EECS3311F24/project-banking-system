package com.example.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter

public class TransferRequest {
    private String fromAccountNumber;
    private String toAccountNumber;
    private double amount;
}
