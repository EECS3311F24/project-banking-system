package bankingSystemBackend.model;

import java.util.UUID;

public class Account {
    private UUID accountID;
    private int accountBalance;
    
    public Account(UUID accountID, int accountBalance) {
        this.accountID = accountID;
        this.accountBalance = accountBalance;
    }

    public UUID getAccountID() {
        return accountID;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

}
