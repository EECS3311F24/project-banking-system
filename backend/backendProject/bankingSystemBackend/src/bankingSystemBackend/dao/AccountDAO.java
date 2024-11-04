package bankingSystemBackend.dao;

import java.util.List;
import java.util.UUID;

import bankingSystemBackend.model.Account;
import bankingSystemBackend.model.CustomerInfo;
import bankingSystemBackend.model.Transaction;

public interface AccountDAO {
    boolean insertAccount(UUID id, double amount); // create account   
    boolean insertAccountConnection(UUID aID, UUID cID); // create accountOwners row
    
    Account getAccountFromID(UUID aID);
    
    List<UUID> getAccountIDsFromCustomerID(UUID cID);
    List<UUID> getCustomerIDsFromAccountID(UUID aID);
    
    List<Transaction> getTransactionsFromAccountID(UUID aID);
    
    boolean changeBalance(UUID aID, String typeOfTransaction, double amount);
    
    boolean removeAssociatedCustomerFromAccount(UUID aID, UUID cID);
    boolean removeAccount(UUID aID);
    
    default boolean addAccount(UUID cID){
        UUID id = UUID.randomUUID();
        return insertAccount(id, 0) && insertAccountConnection(id, cID);
    }
} 