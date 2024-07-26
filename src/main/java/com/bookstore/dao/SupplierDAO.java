package main.java.com.bookstore.dao;

import main.java.com.bookstore.model.Supplier;
import main.java.com.bookstore.util.ConnectionHelper;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {

    private Connection conn;

    public SupplierDAO() {
        conn = ConnectionHelper.getConnection();
    }

    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        String query = "SELECT SUPPLIERID, SNAME, SPHONE, SEMAIL, SADDRESS FROM SUPPLIER";
        try (
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Supplier supplier = new Supplier();
                supplier.setSupplierId(rs.getString("SUPPLIERID"));
                supplier.setName(rs.getString("SNAME"));
                supplier.setPhone(rs.getString("SPHONE"));
                supplier.setEmail(rs.getString("SEMAIL"));
                supplier.setAddress(rs.getString("SADDRESS"));
                suppliers.add(supplier);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    public Supplier getSupplierById(String supplierId) {
        Supplier supplier = null;
        String query = "SELECT SUPPLIERID, SNAME, SPHONE, SEMAIL, SADDRESS FROM SUPPLIER WHERE SUPPLIERID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, supplierId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    supplier = new Supplier();
                    supplier.setSupplierId(rs.getString("SUPPLIERID"));
                    supplier.setName(rs.getString("SNAME"));
                    supplier.setPhone(rs.getString("SPHONE"));
                    supplier.setEmail(rs.getString("SEMAIL"));
                    supplier.setAddress(rs.getString("SADDRESS"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return supplier;
    }

    public Supplier getSupplierByName(String name) {
        Supplier supplier = null;
        String query = "SELECT SUPPLIERID, SNAME FROM SUPPLIER WHERE SNAME = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    supplier = new Supplier();
                    supplier.setSupplierId(rs.getString("SUPPLIERID"));
                    supplier.setName(rs.getString("SNAME"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return supplier;
    }

    public void loadSuppliers(final JComboBox<String> comboBox) {
        SwingWorker<List<Supplier>, Void> worker = new SwingWorker<List<Supplier>, Void>() {
            @Override
            protected List<Supplier> doInBackground() throws Exception {
                return getAllSuppliers();
            }

            @Override
            protected void done() {
                try {
                    List<Supplier> suppliers = get();
                    comboBox.removeAllItems();
                    for (Supplier supplier : suppliers) {
                        comboBox.addItem(supplier.getName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }

    public void addSupplier(Supplier supplier) {
        String query = "INSERT INTO SUPPLIER (SUPPLIERID, SNAME, SPHONE, SEMAIL, SADDRESS) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, getNextSupplierId());
            pst.setString(2, supplier.getName());
            pst.setString(3, supplier.getPhone());
            pst.setString(4, supplier.getEmail());
            pst.setString(5, supplier.getAddress());
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateSupplier(Supplier supplier) {
        String query = "UPDATE SUPPLIER SET SNAME = ?, SPHONE = ?, SEMAIL = ?, SADDRESS = ? WHERE SUPPLIERID = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, supplier.getName());
            pst.setString(2, supplier.getPhone());
            pst.setString(3, supplier.getEmail());
            pst.setString(4, supplier.getAddress());
            pst.setString(5, supplier.getSupplierId());
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteSupplier(String supplierId) {
        String query = "DELETE FROM SUPPLIER WHERE SUPPLIERID = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, supplierId);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getNextSupplierId() {
        String query = "SELECT MAX(SUPPLIERID) AS MAX_ID FROM SUPPLIER";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                String maxId = rs.getString("MAX_ID");
                if (maxId != null && maxId.startsWith("SUPI-")) {
                    int idNumber = Integer.parseInt(maxId.substring(5));
                    return String.format("SUPI-%04d", idNumber + 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "SUPI-0001";
    }
}
