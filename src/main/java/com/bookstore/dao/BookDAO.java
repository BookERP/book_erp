package main.java.com.bookstore.dao;

import main.java.com.bookstore.dao.Crud;
import main.java.com.bookstore.model.Book;
import main.java.com.bookstore.util.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BookDAO implements Crud<Book> {
    private Connection conn;

    public BookDAO() {
        conn = ConnectionHelper.getConnection();
    }

    @Override
    public void create(Book book) {
        String sql = "INSERT INTO book (bookid, isbn, , author, publisher, publish_date, price, category, stock) " +
                "VALUES (book_seq.nextval,?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getBookId());
            pstmt.setString(2, book.getIsbn());
            pstmt.setString(3, book.getAuthor());
            pstmt.setString(4, book.getPublisher());
            pstmt.setString(5, book.getPublishDate());
            pstmt.setDouble(6, book.getPrice());
            pstmt.setString(7, book.getCategory());
            pstmt.setString(8, book.getCurrent());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Book read(String bookId) {
        String sql = "SELECT * FROM book WHERE book_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, bookId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getString("book_id"));
                book.setIsbn(rs.getString("isbn"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setPublishDate(rs.getString("publish_date"));
                book.setPrice(rs.getDouble("price"));
                book.setCategory(rs.getString("category"));
                book.setCurrent(rs.getString("stock"));
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Book book) {
        String sql = "UPDATE book SET isbn = ?, title = ?, author = ?, publisher = ?, " +
                "publish_date = ?, price = ?, category = ?, stock = ? WHERE book_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getIsbn());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getPublisher());
            pstmt.setString(4, book.getPublishDate());
            pstmt.setDouble(5, book.getPrice());
            pstmt.setString(6, book.getCategory());
            pstmt.setString(7, book.getCurrent());
            pstmt.setString(8, book.getBookId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String bookId) {
        String sql = "DELETE FROM book WHERE book_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, bookId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> readAll() {
        String sql = "SELECT * FROM book";
        List<Book> books = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getString("book_id"));
                book.setIsbn(rs.getString("isbn"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setPublishDate(rs.getString("publish_date"));
                book.setPrice(rs.getDouble("price"));
                book.setCategory(rs.getString("category"));
                book.setCurrent(rs.getString("Current"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}