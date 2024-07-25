package src.main.java.com.bookstore.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import src.main.java.com.bookstore.model.Customer;
import src.main.java.com.bookstore.util.ConnectionHelper;

public class CustomerDAO {
    private Connection conn;

    public CustomerDAO() {
        conn = ConnectionHelper.getConnection();
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM CUSTOMER";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Customer customer = new Customer();
//                customer.setCustomerId(rs.getString("CUSTOMERID"));
//                customer.setCName(rs.getString("CNAME"));
//                customer.setCPhone(rs.getString("CPHONE"));
//                customer.setCEmail(rs.getString("CEMAIL"));
//                customer.setCAddress(rs.getString("CADDRESS"));
//                customer.setRDate(rs.getDate("RDATE"));
//                customer.setCpw(rs.getString("CPW"));
                customer.setCustomerId(rs.getString("CUSTOMERID"));
                customer.setCName(rs.getString("NAME"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public Customer getCustomerByID(String customerId) {
        Customer customer = null;
        String query = "SELECT * FROM CUSTOMER WHERE CUSTOMERID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, customerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                customer = new Customer();
//                customer.setCustomerId(rs.getString("CUSTOMERID"));
//                customer.setCName(rs.getString("CNAME"));
//                customer.setCPhone(rs.getString("CPHONE"));
//                customer.setCEmail(rs.getString("CEMAIL"));
//                customer.setCAddress(rs.getString("CADDRESS"));
//                customer.setRDate(rs.getDate("RDATE"));
//                customer.setCpw(rs.getString("CPW"));
                customer.setCName(rs.getString("NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public void updateCustomer(Customer customer) {
        String query = "UPDATE CUSTOMER SET CUSTOMERID = ?, CNAME = ?, CPHONE = ?, CEMAIL = ?, CADDRESS = ?, CPW = ? WHERE CUSTOMERID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, customer.getCustomerId());
            pstmt.setString(2, customer.getCName());
            pstmt.setString(3, customer.getCPhone());
            pstmt.setString(4, customer.getCEmail());
            pstmt.setString(5, customer.getCAddress());
            pstmt.setString(6, customer.getCpw());
            pstmt.setString(7, customer.getCustomerId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomer(String customerId) {
        String query = "DELETE FROM CUSTOMER WHERE CUSTOMERID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, customerId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}