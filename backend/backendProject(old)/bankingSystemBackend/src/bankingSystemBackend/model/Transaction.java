package bankingSystemBackend.model;

import java.util.Date;
import java.util.UUID;

public class Transaction {
	private final UUID aID;
	private final String transactionType;
	private final double amount;
	private final Date transactionDate;
	
	public Transaction(UUID aID, String transactionType, double amount) {
		this.aID = aID;
		this.transactionType = transactionType;
		this.amount = amount;
		this.transactionDate = new Date();
	}
	
	public UUID getAID() {
		return this.aID;
	}
	
	public String getTransactionType() {
		return this.transactionType;
	}
	
	public double getAmount() {
		return this.amount;
	}
	
	public Date getDate() {
		return this.transactionDate;
	}
	
	public String toString() {
		return this.aID.toString() + " " + this.transactionType + " " + this.amount + " " + this.transactionDate.toString();
	}
}
