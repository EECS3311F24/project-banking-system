package bankingSystemBackend.service;

import java.util.List;
import java.util.UUID;

import bankingSystemBackend.dao.daos.AccountDAO;
import bankingSystemBackend.model.Account;
import bankingSystemBackend.model.CustomerInfo;
import bankingSystemBackend.model.Transaction;

public class AccountService {
	private final AccountDAO accountDAO;
	
	public AccountService(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}
	
	public boolean addAccount(UUID cID) {
		return accountDAO.addAccount(cID);
	}
	
	public boolean insertAccount(UUID id, double amount) { // help method
		return accountDAO.insertAccount(id, amount);
	}
    
    public Account getAccountFromID(UUID aID) {
    	return accountDAO.getAccountFromID(aID);
    }
    
    public boolean changeBalance(UUID aID, String typeOfTransaction, double amount) {
    	final double MINUMUM_BALANCE = 0;
    	if (getAccountFromID(aID).getAccountBalance() - amount < MINUMUM_BALANCE) return false;
    	
    	return accountDAO.changeBalance(aID, typeOfTransaction, amount);
    }
    
    public void toggleLocked(UUID aID) {
    	accountDAO.toggleLocked(aID);
    }
    
    
    public boolean removeAccount(UUID aID) {
    	return accountDAO.removeAccount(aID);
    }
}
