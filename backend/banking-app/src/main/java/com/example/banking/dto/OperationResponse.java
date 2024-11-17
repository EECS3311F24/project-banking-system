package com.example.banking.dto;

public class OperationResponse {
    private String message;
    private double balance;

    public OperationResponse(String message, double balance) {
        this.message = message;
        this.balance = balance;
    }

    // Getters and setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
