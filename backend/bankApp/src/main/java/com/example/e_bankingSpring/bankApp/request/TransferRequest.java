package com.example.e_bankingSpring.bankApp.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {
    private String fromEmail;
    private String toEmail;
    private double amount;
}
