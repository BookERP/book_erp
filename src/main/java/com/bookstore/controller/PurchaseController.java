package main.java.com.bookstore.controller;

import main.java.com.bookstore.model.Book;
import main.java.com.bookstore.model.Customer;
import main.java.com.bookstore.model.Purchase;
import main.java.com.bookstore.service.BookService;
import main.java.com.bookstore.service.CustomerService;
import main.java.com.bookstore.service.PurchaseService;

import java.util.List;

public class PurchaseController {
    private PurchaseService purchaseService;
    private BookService bookService;
    private CustomerService customerService;

    public PurchaseController() {
        purchaseService = new PurchaseService();
        bookService = new BookService();
        customerService = new CustomerService();
    }

    public void purchaseBook(String bookTitle, String customerName, String purchaseDate) {
        Book book = bookService.searchBookByTitle(bookTitle);
        Customer customer = customerService.searchCustomerByName(customerName);

        if (book != null && customer != null) {
            Purchase purchase = new Purchase();
            purchase.setBookId(book.getBookId());
            purchase.setCustomerId(customer.getCustomerId());
            purchase.setPurchaseDate(java.sql.Date.valueOf(purchaseDate));

            purchaseService.addPurchase(purchase);
        } else {
            // 예외 처리
        }
    }

    public String[] getBookTitles() {
        List<Book> books = bookService.getAllBooks();
        String[] bookTitles = new String[books.size()];
        for (int i = 0; i < books.size(); i++) {
            bookTitles[i] = books.get(i).getTitle();
        }
        return bookTitles;
    }

    public String[] getCustomerNames() {
        List<Customer> customers = customerService.getAllCustomers();
        String[] customerNames = new String[customers.size()];
        for (int i = 0; i < customers.size(); i++) {
            customerNames[i] = customers.get(i).getName();
        }
        return customerNames;
    }
}
