package main.java.com.bookstore.service;

import main.java.com.bookstore.dao.CustomerDAO;
import main.java.com.bookstore.model.Customer;

import java.util.List;

public class CustomerService {
    private CustomerDAO customerDAO;

    public CustomerService() {
        customerDAO = new CustomerDAO();
    }

    // 고객 추가
    public void addCustomer(Customer customer) {
        // 고객 정보 유효성 검사
        if (customer.getName().isEmpty() || customer.getPhone().isEmpty()) {
            throw new IllegalArgumentException("고객 정보가 유효하지 않습니다.");
        }
        customerDAO.create(customer);
    }

    // 고객 수정
    public void updateCustomer(Customer customer) {
        // 고객 정보 유효성 검사
        if (customer.getCustomerId().isEmpty() || customer.getName().isEmpty()) {
            throw new IllegalArgumentException("고객 정보가 유효하지 않습니다.");
        }
        customerDAO.update(customer);
    }

    // 고객 삭제
    public void deleteCustomer(String customerId) {
        customerDAO.delete(customerId);
    }

    // 고객 검색
    public Customer searchCustomer(String customerId) {
        return customerDAO.read(customerId);
    }

    // 모든 고객 조회
    public List<Customer> getAllCustomers() {
        return customerDAO.readAll();
    }


    // 고객 이름으로 검색
    public Customer searchCustomerByName(String customerName) {
        return customerDAO.read(customerName);
    }
}