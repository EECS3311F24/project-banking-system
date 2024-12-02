package bankingSystemBackend.dao.daos;

import java.util.List;
import java.util.UUID;

import bankingSystemBackend.model.Transaction;

public interface TransactionDAO {
	boolean insertTransaction(UUID aID, String transactionType, double amount);
	
	List<Transaction> getTransactionsFromAccountID(UUID aID);
}
