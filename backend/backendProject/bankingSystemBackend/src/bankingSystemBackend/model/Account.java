package bankingSystemBackend.model;

import java.util.UUID;

public class Account {
    private UUID accountID;
    private double accountBalance;
    
    public Account(UUID accountID, double accountBalance) {
        this.accountID = accountID;
        this.accountBalance = accountBalance;
    }

    public UUID getAccountID() {
        return accountID;
    }

    public double getAccountBalance() {
        return accountBalance;
    }
    
    public void setAccountBalance(double newBal) {
    	this.accountBalance = newBal;
    }

}
