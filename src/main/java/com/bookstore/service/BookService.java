package main.java.com.bookstore.service;

import main.java.com.bookstore.dao.BookDAO;
import main.java.com.bookstore.model.Book;

import java.util.List;

public class BookService {
    private BookDAO bookDAO;

    public BookService() {
        bookDAO = new BookDAO();
    }

    // 도서 추가
    public void addBook(Book book) {
        // 도서 정보 유효성 검사
        if (book.getIsbn().isEmpty() || book.getAuthor().isEmpty()) {
            throw new IllegalArgumentException("도서 정보가 유효하지 않습니다.");
        }
        bookDAO.create(book);
    }

    // 도서 수정
    public void updateBook(Book book) {
        // 도서 정보 유효성 검사
        if (book.getBookId().isEmpty() || book.getIsbn().isEmpty()) {
            throw new IllegalArgumentException("도서 정보가 유효하지 않습니다.");
        }
        bookDAO.update(book);
    }

    // 도서 삭제
    public void deleteBook(String bookId) {
        bookDAO.delete(bookId);
    }

    // 도서 검색
    public Book searchBook(String bookId) {
        return bookDAO.read(bookId);
    }

    // 모든 도서 조회
    public List<Book> getAllBooks() {
        return bookDAO.readAll();
    }
}