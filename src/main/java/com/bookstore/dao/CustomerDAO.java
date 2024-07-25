package main.java.com.bookstore.dao;

import main.java.com.bookstore.model.Customer;
import main.java.com.bookstore.util.ConnectionHelper;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// dao/CustomerDAO.java
public class CustomerDAO implements Crud<Customer> {
    private Connection conn;

    public CustomerDAO() {
        conn = ConnectionHelper.getConnection();
    }

    @Override
    public void create(Customer customer) {
        String sql = "INSERT INTO customer (name, phone) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getPhone());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer read(String customerId) {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getString("customer_id"));
                customer.setName(rs.getString("name"));
                customer.setPhone(rs.getString("phone"));
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Customer customer) {
        String sql = "UPDATE customer SET name = ?, phone = ? WHERE customer_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getPhone());
            pstmt.setString(3, customer.getCustomerId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String customerId) {
        String sql = "DELETE FROM customer WHERE customer_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customerId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> readAll() {
        String sql = "SELECT * FROM customer";
        List<Customer> customers = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getString("customer_id"));
                customer.setName(rs.getString("name"));
                customer.setPhone(rs.getString("phone"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}