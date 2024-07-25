package main.java.com.bookstore.controller;

import main.java.com.bookstore.model.Customer;
import main.java.com.bookstore.model.Purchase;
import main.java.com.bookstore.model.Rental;
import main.java.com.bookstore.service.CustomerService;
import main.java.com.bookstore.service.PurchaseService;
import main.java.com.bookstore.service.RentalService;

import java.util.List;

public class CustomerController {
    private CustomerService customerService;
    private RentalService rentalService;
    private PurchaseService purchaseService;


    public CustomerController() {
        customerService = new CustomerService();
        rentalService = new RentalService();
        purchaseService = new PurchaseService();
    }

    // 고객 추가
    public void addCustomer(Customer customer) {
        customerService.addCustomer(customer);
    }

    // 고객 수정
    public void updateCustomer(Customer customer) {
        customerService.updateCustomer(customer);
    }

    // 고객 삭제
    public void deleteCustomer(String customerId) {
        customerService.deleteCustomer(customerId);
    }

    // 고객 검색
    public Customer searchCustomer(String customerId) {
        return customerService.searchCustomer(customerId);
    }
    // 이름으로 고객 검색
    public List<Customer> searchCustomers(String keyword) {
        return (List<Customer>) customerService.searchCustomer(keyword);
    }

    // 모든 고객 조회
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
    // 고객의 대여 목록 조회
    public List<Rental> getCustomerRentals(String customerId) {
        return rentalService.getCustomerRentals(customerId);
    }

    // 고객의 구매 목록 조회
    public List<Purchase> getCustomerPurchases(String customerId) {
        return purchaseService.getCustomerPurchases(customerId);
    }
}