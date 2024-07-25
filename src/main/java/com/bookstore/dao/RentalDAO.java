package main.java.com.bookstore.dao;

import main.java.com.bookstore.model.Rental;
import main.java.com.bookstore.util.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentalDAO extends ConnectionHelper {
    public RentalDAO() {
        super();
    }


    // 대여 정보 추가
    public void create(Rental rental) {
        String sql = "INSERT INTO rental (book_id, customer_id, rental_date, return_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, rental.getBookId());
            pstmt.setString(2, rental.getCustomerId());
            pstmt.setDate(3, new java.sql.Date(rental.getRentalDate().getTime()));
            pstmt.setDate(4, new java.sql.Date(rental.getReturnDate().getTime()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 대여 정보 조회
    public Rental read(String rentalId) {
        String sql = "SELECT * FROM rental WHERE rental_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, rentalId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Rental rental = new Rental();
                rental.setRentalId(rs.getString("rental_id"));
                rental.setBookId(rs.getString("book_id"));
                rental.setCustomerId(rs.getString("customer_id"));
                rental.setRentalDate(rs.getDate("rental_date"));
                rental.setReturnDate(rs.getDate("return_date"));
                return rental;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 대여 정보 수정
    public void update(Rental rental) {
        String sql = "UPDATE rental SET book_id = ?, customer_id = ?, rental_date = ?, return_date = ? WHERE rental_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, rental.getBookId());
            pstmt.setString(2, rental.getCustomerId());
            pstmt.setDate(3, new java.sql.Date(rental.getRentalDate().getTime()));
            pstmt.setDate(4, new java.sql.Date(rental.getReturnDate().getTime()));
            pstmt.setString(5, rental.getRentalId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 대여 정보 삭제
    public void delete(String rentalId) {
        String sql = "DELETE FROM rental WHERE rental_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, rentalId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 모든 대여 정보 조회
    public List<Rental> readAll() {
        String sql = "SELECT * FROM rental";
        List<Rental> rentals = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Rental rental = new Rental();
                rental.setRentalId(rs.getString("rental_id"));
                rental.setBookId(rs.getString("book_id"));
                rental.setCustomerId(rs.getString("customer_id"));
                rental.setRentalDate(rs.getDate("rental_date"));
                rental.setReturnDate(rs.getDate("return_date"));
                rentals.add(rental);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentals;
    }

    // 고객의 대여 목록 조회
    // 고객 ID를 기반으로 대여 정보를 조회하는 쿼리 실행
    // 조회 결과를 Rental 객체로 변환하여 리스트에 추가
    // * java.util.Date 타입을 java.sql.Date로 변환하여 전달
    public List<Rental> readByCustomerId(String customerId) {
        String sql = "SELECT * FROM rental WHERE customer_id = ?";
        List<Rental> rentals = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customerId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Rental rental = new Rental();
                rental.setRentalId(rs.getString("rental_id"));
                rental.setBookId(rs.getString("book_id"));
                rental.setCustomerId(rs.getString("customer_id"));
                rental.setRentalDate(rs.getDate("rental_date"));
                rental.setReturnDate(rs.getDate("return_date"));
                rentals.add(rental);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentals;
    }
}