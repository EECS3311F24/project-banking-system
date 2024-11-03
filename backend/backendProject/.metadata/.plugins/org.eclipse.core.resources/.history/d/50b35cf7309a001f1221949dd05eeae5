package bankingSystemBackend.dao;

import java.util.List;
import java.util.UUID;

import bankingSystemBackend.model.CustomerInfo;

public interface CustomerInfoDAO {
    boolean insertCustomerInfo(UUID id, CustomerInfo customer);
    
    List<CustomerInfo> getAllCustomerInfo();
    
    CustomerInfo getCustomerInfoFromMailAndPass(String mail, String pass);

    boolean changeCustomerInfo(String email, String oldPassword, String newPassword, String newMobileNumber, String newAddress);
    
    boolean removeCustomer(String email, String password);

    default boolean addCustomerInfo(CustomerInfo customer){
        UUID id = UUID.randomUUID();
        return insertCustomerInfo(id, customer);
    }
}
