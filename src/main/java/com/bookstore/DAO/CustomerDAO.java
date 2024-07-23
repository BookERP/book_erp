package main.java.com.bookstore.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import main.java.com.bookstore.model.Customer;
import main.java.com.bookstore.model.Inventory;
import main.java.com.bookstore.util.ConnectionHelper;

public class CustomerDAO {
	
	private Connection conn;

    public CustomerDAO() {
        conn = ConnectionHelper.getConnection();
    }
    
    //전체보기
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM CUSTOMER";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
            	Customer customer = new Customer();
            	customer.setCustomerId(rs.getString("CUSTOMERID"));
            	customer.setName(rs.getString("NAME"));
            	customer.setPhone(rs.getString("PHONE"));
            	customer.setEmail(rs.getString("EMAIL"));
            	customer.setAddress(rs.getString("ADDRESS"));
            	customer.setRDate(rs.getDate("RDATE"));
            	customer.setCustomerCpw(rs.getString("CUSTOMERCPW"));
            	customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
    //검색
	public Customer getCustomerById(String CustomerId) {
		Customer customer = null;
		String query = "SELECT * FROM CUSTOMER WHERE CUSTOMERID = ?";
		// ?의 경우 로그인할 때 저장된 아이디를 가져와야 한다/
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, CustomerId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				customer = new Customer();
				customer.setCustomerId(rs.getString("CUSTOMERID"));
				customer.setName(rs.getString("NAME"));
				customer.setPhone(rs.getString("PHONE"));
				customer.setEmail(rs.getString("EMAIL"));
				customer.setAddress(rs.getString("ADRESS"));
				customer.setRDate(rs.getDate("RDATE"));
				customer.setCustomerCpw(rs.getString("CUSTOMERCPW"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}
	//수정
	public void updateCustomer(Customer customer) {
        String query = "UPDATE CUSTOMER SET CUSTOMERID = ?, NAME = ?, PHONE = ?, EMAIL = ?, ADDRESS = ?, CUSTOMERCPW = ? WHERE CUSTOMERID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        	customer = new Customer();
        	String cs = customer.getCustomerId();
            pstmt.setString(1, cs);
            pstmt.setString(2, customer.getName());
            pstmt.setString(3, customer.getPhone());
            pstmt.setString(4, customer.getEmail());
            pstmt.setString(5, customer.getAddress());
            pstmt.setString(6, customer.getCustomerCpw());            
            pstmt.setString(7, cs);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void deleteCustomer(String customerId) {
        String query = "DELETE FROM CUSTOMER WHERE CUSTOMERTID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, customerId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
