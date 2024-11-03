package bankingSystemBackend.model;

import java.util.UUID;

public class CustomerInfo {
    private final UUID customerID;
    private final String email;
    private String password;
    private String mobileNum;
    private String address;

    public CustomerInfo(UUID id, String mail, String pass, String mobileNum, String address){
        this.customerID = id;
        this.email = mail;
        this.password = pass;
        this.mobileNum = mobileNum;
        this.address = address;
    }

    public UUID getCustomerID() {
        return customerID;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public String getAddress() {
        return address;
    }
    
    public void setPassword(String password) {
    	this.password = password;
    }
    
    public void setMobileNum(String mobileNum) {
    	this.mobileNum = mobileNum;
    }
    
    public void setAddress(String address) {
    	this.address = address;
    }
}
