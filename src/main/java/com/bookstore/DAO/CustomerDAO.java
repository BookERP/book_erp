package main.java.com.bookstore.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.com.bookstore.model.Customer;
import main.java.com.bookstore.util.ConnectionHelper;

public class CustomerDAO {
	
	private Connection conn;

    public CustomerDAO() {
        conn = ConnectionHelper.getConnection();
    }
    
    private String customerID;
	
	public Customer selectMyCustomer(String CustomerID) throws SQLException {
		Customer customer = null;
		String query = "SELECT CUSTOMERID, customerCpw, NAME, PHONE, EMAIL, ADDRESS, RDATE FROM Customer where CustomerID = ?";
		// ?의 경우 로그인할 때 저장된 아이디를 가져와야 한다/
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, CustomerID);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				customer = new Customer();
                customer.setCustomerId(rs.getString("CUSTOMERID"));
                customer.setCustomerCpw(rs.getString("customerCpw"));
                customer.setCustomerName(rs.getString("NAME"));
                customer.setCustomerPhone(rs.getString("PHONE"));
                customer.setCustomerEmail(rs.getString("EMAIL"));
                customer.setCustomerAddress(rs.getString("ADDRESS"));
                customer.setCustomerRdate(rs.getDate("RDATE"));
                
                
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}

	public List<Customer> selectAllCustomer() {
		List<Customer> customers = new ArrayList<>();
		String query = "SELECT CUSTOMERID, customerCpw, NAME, PHONE, EMAIL, ADDRESS, RDATE FROM Customer";
		// ?의 경우 로그인할 때 저장된 아이디를 가져와야 한다/
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerId(rs.getString("CUSTOMERID"));
				customer.setCustomerCpw(rs.getString("customerCpw"));
				customer.setCustomerName(rs.getString("NAME"));
				customer.setCustomerPhone(rs.getString("PHONE"));
				customer.setCustomerId(rs.getString("EMAIL"));
				customer.setCustomerAddress(rs.getString("ADDRESS"));
				customer.setCustomerRdate(rs.getDate("RDATE"));
                customers.add(customer);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} 
		return customers;
	}
	public void setCustomerId(String customerID) {
		this.customerID = customerID;
	}
}