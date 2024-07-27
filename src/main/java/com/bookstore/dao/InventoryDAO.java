package main.java.com.bookstore.dao;

import main.java.com.bookstore.model.Inventory;
import main.java.com.bookstore.util.ConnectionHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {
	
	private Connection conn;

    public InventoryDAO() {
        conn = ConnectionHelper.getConnection();
    }
	
    public List<Inventory> getAllInventories() {
        List<Inventory> inventories = new ArrayList<>();
        String query = "SELECT * FROM INVENTORY";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Inventory inventory = new Inventory();
                inventory.setInventoryId(rs.getString("INVENTORYID"));
                inventory.setProductId(rs.getString("PID"));
                inventory.setCurrentQuantity(rs.getInt("CURRENTQ")); 
                inventory.setLocation(rs.getString("LOCATION"));
                inventories.add(inventory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventories;
    }

    public Inventory getInventoryById(String inventoryId) {
        Inventory inventory = null;
        String query = "SELECT * FROM INVENTORY WHERE INVENTORYID = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, inventoryId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    inventory = new Inventory();
                    inventory.setInventoryId(rs.getString("INVENTORYID"));
                    inventory.setProductId(rs.getString("PID"));
                    inventory.setCurrentQuantity(rs.getInt("CURRENTQ")); 
                    inventory.setLocation(rs.getString("LOCATION"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventory;
    }

    public void addInventory(Inventory inventory) {
        String query = "INSERT INTO INVENTORY (INVENTORYID, PID, CURRENTQ, LOCATION) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, inventory.getInventoryId());
            pst.setString(2, inventory.getProductId());
            pst.setInt(3, inventory.getCurrentQuantity());
            pst.setString(4, inventory.getLocation());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteInventory(String inventoryId) {
        String query = "DELETE FROM INVENTORY WHERE INVENTORYID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, inventoryId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Inventory deleted successfully.");
            } else {
                System.out.println("No inventory found with ID: " + inventoryId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }	

    public String getNextInventoryId() {
        String query = "SELECT MAX(INVENTORYID) AS MAX_ID FROM INVENTORY";
        try (
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                String maxId = rs.getString("MAX_ID");
                if (maxId != null) {
                    // Assuming the format is INVN-0001
                    String[] parts = maxId.split("-");
                    if (parts.length == 2) {
                        int idNumber = Integer.parseInt(parts[1]);
                        return String.format("INVN-%04d", idNumber + 1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "INVN-0001";
    }
}
