package bankingSystemBackend.model;

import java.util.UUID;

public class Account {
    private UUID accountID;
    private String accountName;
    private double accountBalance;
    private boolean locked;
    
    public Account(UUID accountID, String accountName, double accountBalance, boolean locked) {
        this.accountID = accountID;
        this.accountName = accountName;
        this.accountBalance = accountBalance;
        this.locked = locked;
    }

    public UUID getAccountID() {
        return accountID;
    }
    
    public String getAccountName() {
    	return accountName;
    }

    public double getAccountBalance() {
        return accountBalance;
    }
    
    public boolean isLocked() {
    	return locked;
    }
    
    public void setAccountName(String newName) {
    	this.accountName = newName;
    }
    
    public void setAccountBalance(double newBal) {
    	this.accountBalance = newBal;
    }
    
    public void toggleLock() {
    	locked = !locked;
    }

}
