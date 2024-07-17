<<<<<<< HEAD
package src.main.java.com.bookstore.dao;

import src.main.java.com.bookstore.model.Product;
import src.main.java.com.bookstore.util.ConnectionHelper;
=======
package main.java.com.bookstore.dao;

import main.java.com.bookstore.model.Product;
import main.java.com.bookstore.util.ConnectionHelper;
>>>>>>> 1dfa6bbc17224b955e85a46774d317d7b18704ab

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        Connection conn = ConnectionHelper.getConnection("oracle");
        String query = "SELECT * FROM PRODUCT";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getString("PRODUCTID"));
                product.setSupplierid(rs.getString("SUPPLIERID"));
                product.setName(rs.getString("NAME"));
                product.setAuthor(rs.getString("AUTHOR"));
                product.setPublisher(rs.getString("PUBLISHER"));
                product.setPrice(rs.getDouble("PRICE"));
                product.setStuckQuantity(rs.getInt("STOCKQ"));
                product.setCategory(rs.getString("CATEGORY"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void addProduct(Product product) {
        Connection conn = ConnectionHelper.getConnection("oracle");
        String query = "INSERT INTO PRODUCT (PRODUCTID, SUPPLIERID, NAME, AUTHOR, PUBLISHER, PRICE, STOCKQ, CATEGORY) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, product.getProductId());
            pstmt.setString(2, product.getSupplierid());
            pstmt.setString(3, product.getName());
            pstmt.setString(4, product.getAuthor());
            pstmt.setString(5, product.getPublisher());
            pstmt.setDouble(6, product.getPrice());
            pstmt.setInt(7, product.getStuckQuantity());
            pstmt.setString(8, product.getCategory());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(Product product) {
        Connection conn = ConnectionHelper.getConnection("oracle");
        String query = "UPDATE PRODUCT SET SUPPLIERID = ?, NAME = ?, AUTHOR = ?, PUBLISHER = ?, PRICE = ?, STOCKQ = ?, CATEGORY = ? WHERE PRODUCTID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, product.getSupplierid());
            pstmt.setString(2, product.getName());
            pstmt.setString(3, product.getAuthor());
            pstmt.setString(4, product.getPublisher());
            pstmt.setDouble(5, product.getPrice());
            pstmt.setInt(6, product.getStuckQuantity());
            pstmt.setString(7, product.getCategory());
            pstmt.setString(8, product.getProductId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(String productId) {
        Connection conn = ConnectionHelper.getConnection("oracle");
        String query = "DELETE FROM PRODUCT WHERE PRODUCTID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, productId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
