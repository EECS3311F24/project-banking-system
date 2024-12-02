package bankingSystemBackend.model;

import java.util.UUID;

public class AccountOwners {
	private UUID cID;
	private UUID aID;
	
	public AccountOwners(UUID cID, UUID aID) {
		this.cID = cID;
		this.aID = aID;
	}
	
	public UUID getCID() {
		return this.cID;
	}
	
	public UUID getAID() {
		return this.aID;
	}
	
	public String toString(){
		return cID.toString() + " " + aID.toString();
	}
}
