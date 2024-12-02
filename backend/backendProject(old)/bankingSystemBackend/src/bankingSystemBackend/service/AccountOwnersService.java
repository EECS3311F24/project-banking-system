package bankingSystemBackend.service;

import java.util.List;
import java.util.UUID;

import bankingSystemBackend.dao.daos.AccountOwnersDAO;

public class AccountOwnersService {
	private final AccountOwnersDAO accountOwnersDAO;
	
	public AccountOwnersService(AccountOwnersDAO accountOwnersDAO) {
		this.accountOwnersDAO = accountOwnersDAO;
	}
	
	public boolean insertAccountConnection(UUID cID, UUID aID) {
		return accountOwnersDAO.insertAccountConnection(cID, aID);
	}
	
	public List<UUID> getAccountIDsFromCustomerID(UUID cID){
		return accountOwnersDAO.getAccountIDsFromCustomerID(cID);
	}
	
    public List<UUID> getCustomerIDsFromAccountID(UUID aID){
    	return accountOwnersDAO.getCustomerIDsFromAccountID(aID);
    }
    
    public boolean removeAssociatedCustomerFromAccount(UUID cID, UUID aID) {
    	return accountOwnersDAO.removeAssociatedCustomerFromAccount(cID, aID);
    }
}
