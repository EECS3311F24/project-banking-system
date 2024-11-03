package bankingSystemBackend.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import bankingSystemBackend.model.CustomerInfo;

public class LocalCustomerInfoDataAccessService implements CustomerInfoDAO {

    private static List<CustomerInfo> DB = new ArrayList<>();

    @Override
    public boolean insertCustomerInfo(UUID id, CustomerInfo customer) {
        DB.add(new CustomerInfo(id, customer.getEmail(), customer.getPassword(), customer.getMobileNum(), customer.getAddress()));
        return true;
    }

	@Override
	public List<CustomerInfo> getAllCustomerInfo() {
		return DB;
	}

	@Override
	public CustomerInfo getCustomerInfoFromMailAndPass(String mail, String pass) {
    	List<CustomerInfo> infos = this.getAllCustomerInfo();
    	
    	for (CustomerInfo customer : infos) {
    		if (customer.getEmail().equals(mail) && customer.getPassword().equals(pass))
    			return customer;
    	}
    	return null;
    }
	
	@Override
	public CustomerInfo getCustomerInfoFromID(UUID customerID) {
		List<CustomerInfo> infos = this.getAllCustomerInfo();
    	
    	for (CustomerInfo customer : infos) {
    		if (customer.getCustomerID().equals(customerID))
    			return customer;
    	}
    	return null;
	}

	@Override
	public boolean changeCustomerInfo(UUID customerID, String newPassword, String newMobileNumber,
			String newAddress) {
		CustomerInfo customer = getCustomerInfoFromID(customerID);
		if (customer == null) return false;
		
		if (newPassword != null) customer.setPassword(newPassword);
		if (newMobileNumber != null) customer.setMobileNum(newMobileNumber);
		if (newAddress != null) customer.setAddress(newAddress);
		
		return true;
	}

	@Override
	public boolean removeCustomer(UUID customerID) {
		List<CustomerInfo> infos = this.getAllCustomerInfo();
		return infos.removeIf(customer -> (customer.getCustomerID().equals(customerID)));
	}

    
}
