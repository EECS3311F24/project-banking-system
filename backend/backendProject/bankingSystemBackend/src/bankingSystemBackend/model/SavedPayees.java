package bankingSystemBackend.model;

import java.util.UUID;

public class SavedPayees {
    private String nickname;
    private UUID saverID;
    private UUID payeeID;
    
    public SavedPayees(String nickname, UUID saverID, UUID payeeID) {
        this.nickname = nickname;
        this.saverID = saverID;
        this.payeeID = payeeID;
    }

    public String getNickname() {
        return nickname;
    }

    public UUID getSaverID() {
        return saverID;
    }

    public UUID getPayeeID() {
        return payeeID;
    }
}
