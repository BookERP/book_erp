package main.java.com.bookstore.controller;

import main.java.com.bookstore.model.Book;
import main.java.com.bookstore.model.Customer;
import main.java.com.bookstore.model.Rental;
import main.java.com.bookstore.service.BookService;
import main.java.com.bookstore.service.CustomerService;
import main.java.com.bookstore.service.RentalService;

import java.util.List;

public class RentalController {
    private RentalService rentalService;
    private BookService bookService;
    private CustomerService customerService;

    public RentalController() {
        rentalService = new RentalService();
        bookService = new BookService();
        customerService = new CustomerService();
    }

    public void rentalBook(String bookTitle, String customerName, String rentalDate, String returnDate) {
        Book book = bookService.searchBookByTitle(bookTitle);
        Customer customer = customerService.searchCustomerByName(customerName);

        if (book != null && customer != null) {
            Rental rental = new Rental();
            rental.setBookId(book.getBookId());
            rental.setCustomerId(customer.getCustomerId());
            rental.setRentalDate(java.sql.Date.valueOf(rentalDate));
            rental.setReturnDate(java.sql.Date.valueOf(returnDate));

            rentalService.addRental(rental);
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