package bankingSystemBackend.dao.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

import bankingSystemBackend.dao.daos.CustomerInfoDAO;
import bankingSystemBackend.model.CustomerInfo;

public class SQLCustomerInfoDataAccessService implements CustomerInfoDAO{
	static String dbLink = "mysql-bankingsystem.alwaysdata.net";
	static String dbaccount = "383193_backend";
	static String password = "BankSys3311";
	
	@Override
	public boolean insertCustomerInfo(UUID id, CustomerInfo customer) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException x){
			System.out.println("Driver couldn't be loaded");
			return false;
		}
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + dbLink, dbaccount, password);
			Statement stmt = conn.createStatement();
			
			String sql = String.format(
					"INSERT INTO CustomerInfo VALUES (%s, %s, %s, %s, %s)", 
					id.toString(), 
					customer.getEmail(), 
					customer.getPassword(),
					customer.getMobileNum(),
					customer.getAddress());
			stmt.executeUpdate(sql);
			return true;
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    return false;
		}
	}

	@Override
	public List<CustomerInfo> getAllCustomerInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerInfo getCustomerInfoFromMailAndPass(String mail, String pass) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException x){
			System.out.println("Driver couldn't be loaded");
			return null;
		}
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + dbLink, dbaccount, password);
			Statement stmt = conn.createStatement();
			
			String sql = String.format("SELECT FROM CustomerInfo WHERE customerMail = %s AND customerPass = %s", mail, pass);
			ResultSet r = stmt.executeQuery(sql);
			
			if (!r.first()) return null; // no valid row
			
			CustomerInfo customer = new CustomerInfo(
					UUID.fromString((String) r.getObject(1)),
					r.getString(2),
					r.getString(3),
					r.getString(4),
					r.getString(5),
					r.getString(6));
			
			return customer;
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    return null;
		}
	}

	@Override
	public CustomerInfo getCustomerInfoFromID(UUID customerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changeCustomerInfo(UUID customerID, String newPassword, String newMobileNumber, String newAddress) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeCustomer(UUID customerID) {
		// TODO Auto-generated method stub
		return false;
	}

}
