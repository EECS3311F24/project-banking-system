package bankingSystemBackend.dao.local;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import bankingSystemBackend.dao.daos.AccountOwnersDAO;
import bankingSystemBackend.model.AccountOwners;

public class LocalAccountOwnersDataAccessService implements AccountOwnersDAO{

	private static List<AccountOwners> accountOwnersDB = new ArrayList<>();
	
	@Override
	public boolean insertAccountConnection(UUID cID, UUID aID) {
		accountOwnersDB.add(new AccountOwners(cID, aID));
		
		return true;
	}

	@Override
	public List<UUID> getAccountIDsFromCustomerID(UUID cID) {
		List<UUID> aIDs = new ArrayList<>();
		for (AccountOwners connection : accountOwnersDB) {
			if (connection.getCID().equals(cID)) aIDs.add(connection.getAID());
		}
		return aIDs;
	}

	@Override
	public List<UUID> getCustomerIDsFromAccountID(UUID aID) {
		List<UUID> cIDs = new ArrayList<>();
		for (AccountOwners connection : accountOwnersDB) {
			if (connection.getAID().equals(aID)) cIDs.add(connection.getCID());
		}
		return cIDs;
	}

	@Override
	public boolean removeAssociatedCustomerFromAccount(UUID cID, UUID aID) {
		boolean removed = accountOwnersDB.removeIf(connection -> (connection.getCID().equals(cID) && connection.getAID().equals(aID)));
		
		return removed;
	}

}
