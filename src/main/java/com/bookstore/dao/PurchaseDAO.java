package main.java.com.bookstore.dao;

import main.java.com.bookstore.model.Purchase;
import main.java.com.bookstore.util.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;
public class PurchaseDAO extends ConnectionHelper {
    public PurchaseDAO() {
        super();
    }

    // 구매 정보 추가
    public void create(Purchase purchase) {
        String sql = "INSERT INTO purchase (book_id, customer_id, purchase_date) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, purchase.getBookId());
            pstmt.setString(2, purchase.getCustomerId());
            pstmt.setDate(3, new java.sql.Date(purchase.getPurchaseDate().getTime()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 구매 정보 조회
    public Purchase read(String purchaseId) {
        String sql = "SELECT * FROM purchase WHERE purchase_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, purchaseId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Purchase purchase = new Purchase();
                purchase.setPurchaseId(rs.getString("purchase_id"));
                purchase.setBookId(rs.getString("book_id"));
                purchase.setCustomerId(rs.getString("customer_id"));
                purchase.setPurchaseDate(rs.getDate("purchase_date"));
                return purchase;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 구매 정보 수정
    public void update(Purchase purchase) {
        String sql = "UPDATE purchase SET book_id = ?, customer_id = ?, purchase_date = ? WHERE purchase_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, purchase.getBookId());
            pstmt.setString(2, purchase.getCustomerId());
            pstmt.setDate(3, new java.sql.Date(purchase.getPurchaseDate().getTime()));
            pstmt.setString(4, purchase.getPurchaseId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 구매 정보 삭제
    public void delete(String purchaseId) {
        String sql = "DELETE FROM purchase WHERE purchase_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, purchaseId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 모든 구매 정보 조회
    public List<Purchase> readAll() {
        String sql = "SELECT * FROM purchase";
        List<Purchase> purchases = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Purchase purchase = new Purchase();
                purchase.setPurchaseId(rs.getString("purchase_id"));
                purchase.setBookId(rs.getString("book_id"));
                purchase.setCustomerId(rs.getString("customer_id"));
                purchase.setPurchaseDate(rs.getDate("purchase_date"));
                purchases.add(purchase);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return purchases;
    }

    // 고객의 구매 목록 조회
    public List<Purchase> readByCustomerId(String customerId) {
        String sql = "SELECT * FROM purchase WHERE customer_id = ?";
        List<Purchase> purchases = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customerId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Purchase purchase = new Purchase();
                purchase.setPurchaseId(rs.getString("purchase_id"));
                purchase.setBookId(rs.getString("book_id"));
                purchase.setCustomerId(rs.getString("customer_id"));
                purchase.setPurchaseDate(rs.getDate("purchase_date"));
                purchases.add(purchase);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return purchases;
    }
}