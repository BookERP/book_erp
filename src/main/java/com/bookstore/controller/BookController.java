package main.java.com.bookstore.controller;

import main.java.com.bookstore.model.Book;
import main.java.com.bookstore.service.BookService;

import java.util.List;


public class BookController {
    private BookService bookService;

    public BookController() {
        bookService = new BookService();
    }

    // 도서 추가
    public void addBook(Book book) {
        bookService.addBook(book);
    }

    // 도서 수정
    public void updateBook(Book book) {
        bookService.updateBook(book);
    }

    // 도서 삭제
    public void deleteBook(String bookId) {
        bookService.deleteBook(bookId);
    }

    // 도서 검색
    public Book searchBook(String bookId) {
        return bookService.searchBook(bookId);
    }

    // 모든 도서 조회
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // 키워드로 조회
    public List<Book> searchBooks(String keyword) {
        return bookService.getAllBooks();
    }
}