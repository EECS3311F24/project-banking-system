package bankingSystemBackend.service;

import java.util.List;
import java.util.UUID;

import bankingSystemBackend.dao.daos.TransactionDAO;
import bankingSystemBackend.model.Transaction;

public class TransactionService {
	private final TransactionDAO transactionDAO;
	
	public TransactionService(TransactionDAO transactionDAO) {
		this.transactionDAO = transactionDAO;
	}
	
	public boolean insertTransaction(UUID aID, String transactionType, double amount) {
		return transactionDAO.insertTransaction(aID, transactionType, amount);
	}
	
	public List<Transaction> getTransactionsFromAccountID(UUID aID) {
		return transactionDAO.getTransactionsFromAccountID(aID);
	}
}
