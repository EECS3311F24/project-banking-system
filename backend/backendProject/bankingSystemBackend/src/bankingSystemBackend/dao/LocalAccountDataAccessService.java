package bankingSystemBackend.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import bankingSystemBackend.model.Account;
import bankingSystemBackend.model.AccountOwners;
import bankingSystemBackend.model.CustomerInfo;
import bankingSystemBackend.model.Transaction;

public class LocalAccountDataAccessService implements AccountDAO{
	
	private static List<Account>       accountDB = new ArrayList<>();
	private static List<AccountOwners> accountOwnersDB = new ArrayList<>();
	private static List<Transaction>   transactionDB = new ArrayList<>();

	@Override
	public boolean insertAccount(Account account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertAccountConnection(Account account, CustomerInfo customer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addCustomerToAccount(UUID accountID, CustomerInfo customer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UUID> getAccountIDsFromCustomerID(UUID cID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UUID> getCustomerIDsFromAccountID(UUID aID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Transaction> getTransactionsFromAccountID(UUID aID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changeBalance(UUID aID, String typeOfTransaction, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAssociatedCustomerFromAccount(UUID aID, UUID cID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAccount(UUID aID) {
		// TODO Auto-generated method stub
		return false;
	}
}
