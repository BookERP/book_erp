package src.main.java.com.bookstore.dao;

import src.main.java.com.bookstore.model.Product;
import src.main.java.com.bookstore.util.ConnectionHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
	
	private Connection conn;

    public ProductDAO() {
        conn = ConnectionHelper.getConnection();
    }
	
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM PRODUCT";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getString("PRODUCTID"));
                product.setSupplierId(rs.getString("SID"));
                product.setName(rs.getString("PNAME"));
                product.setAuthor(rs.getString("AUTHOR"));
                product.setPublisher(rs.getString("PUBLISHER"));
                product.setPrice(rs.getDouble("PRICE"));
                product.setStockQuantity(rs.getInt("STOCKQ"));
                product.setCategory(rs.getString("CATEGORY"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
    
    public String getNextProductId() {
        String query = "SELECT MAX(PRODUCTID) AS MAX_ID FROM PRODUCT";
        try(
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                String maxId = rs.getString("MAX_ID");
                if (maxId != null) {
                    String[] parts = maxId.split("-");
                    int idNumber = Integer.parseInt(parts[1]);
                    return String.format("KOSA-%04d", idNumber + 1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "KOSA-0001";
    }
    
    public Product getProductById(String productId) {
        Product product = null;
        String query = "SELECT * FROM PRODUCT WHERE PRODUCTID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, productId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setProductId(rs.getString("PRODUCTID"));
                product.setSupplierId(rs.getString("SID"));
                product.setName(rs.getString("PNAME"));
                product.setAuthor(rs.getString("AUTHOR"));
                product.setPublisher(rs.getString("PUBLISHER"));
                product.setPrice(rs.getDouble("PRICE"));
                product.setStockQuantity(rs.getInt("STOCKQ"));
                product.setCategory(rs.getString("CATEGORY"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public void addProduct(Product product) {
//        String query = "INSERT INTO PRODUCT (PRODUCTID, SID, PNAME, AUTHOR, PUBLISHER, PRICE, CATEGORY) VALUES (?, ?, ?, ?, ?, ?, ?)";
    	String query = "INSERT INTO PRODUCT (PRODUCTID, SID, PNAME, AUTHOR, PUBLISHER, PRICE, CATEGORY) VALUES (?, ?, ?, ?, ?, ?, ?)";
    	try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, product.getProductId());
            pstmt.setString(2, product.getSupplierId());
            pstmt.setString(3, product.getName());
            pstmt.setString(4, product.getAuthor());
            pstmt.setString(5, product.getPublisher());
            pstmt.setDouble(6, product.getPrice());
//            pstmt.setString(7, product.getCategory());
            pstmt.setInt(7, product.getStockQuantity());
            pstmt.setString(8, product.getCategory());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(Product product) {
//        String query = "UPDATE PRODUCT SET SID = ?, PNAME = ?, AUTHOR = ?, PUBLISHER = ?, PRICE = ?, CATEGORY = ? WHERE PRODUCTID = ?";
    	String query = "UPDATE PRODUCT SET SUPPLIERID = ?, NAME = ?, AUTHOR = ?, PUBLISHER = ?, PRICE = ?, STOCKQ = ?, CATEGORY = ? WHERE PRODUCTID = ?";
    	try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, product.getSupplierId());
            pstmt.setString(2, product.getName());
            pstmt.setString(3, product.getAuthor());
            pstmt.setString(4, product.getPublisher());
            pstmt.setDouble(5, product.getPrice());
//            pstmt.setString(6, product.getCategory());
//            pstmt.setString(7, product.getProductId());
            pstmt.setInt(6, product.getStockQuantity());
            pstmt.setString(7, product.getCategory());
            pstmt.setString(8, product.getProductId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public List<String> getAllProductIds() {
        List<String> productIds = new ArrayList<>();
        String query = "SELECT PRODUCTID FROM PRODUCT";

        try (
             PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                productIds.add(rs.getString("PRODUCTID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productIds;
    }

    public void deleteProduct(String productId) {
        String query = "DELETE FROM PRODUCT WHERE PRODUCTID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, productId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
