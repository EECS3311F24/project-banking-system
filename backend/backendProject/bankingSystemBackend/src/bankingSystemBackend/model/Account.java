package bankingSystemBackend.model;

import java.util.UUID;

public class Account {
    private UUID accountID;
    private double accountBalance;
    private boolean locked;
    
    public Account(UUID accountID, double accountBalance, boolean locked) {
        this.accountID = accountID;
        this.accountBalance = accountBalance;
        this.locked = locked;
    }

    public UUID getAccountID() {
        return accountID;
    }

    public double getAccountBalance() {
        return accountBalance;
    }
    
    public boolean isLocked() {
    	return locked;
    }
    
    public void setAccountBalance(double newBal) {
    	this.accountBalance = newBal;
    }
    
    public void toggleLock() {
    	locked = !locked;
    }

}
