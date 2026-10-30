package bankingSystemBackend.dao.daos;

import java.util.UUID;

import bankingSystemBackend.model.Account;

public interface AccountDAO {
    boolean insertAccount(UUID id, String name, double amount); // create account   
    
    Account getAccountFromID(UUID aID);
    
    void changeName(UUID aID, String newName);
    
    boolean changeBalance(UUID aID, String typeOfTransaction, double amount); 
    
    void toggleLocked(UUID aID);
    
    boolean removeAccount(UUID aID);
    
    default boolean addAccount(UUID cID, String name){
        UUID id = UUID.randomUUID();
        return insertAccount(id, name, 0);
    }
} 