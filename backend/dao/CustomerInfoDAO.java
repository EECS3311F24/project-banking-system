package dao;

import java.util.UUID;

import model.CustomerInfo;

public interface CustomerInfoDAO {
    boolean insertCustomerInfo(UUID id, CustomerInfo customer);

    default boolean addCustomerInfo(CustomerInfo customer){
        UUID id = UUID.randomUUID();
        return insertCustomerInfo(id, customer);
    }
}
