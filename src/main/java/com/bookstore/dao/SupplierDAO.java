package src.main.java.com.bookstore.dao;

import src.main.java.com.bookstore.model.Supplier;
import src.main.java.com.bookstore.util.ConnectionHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {

    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        Connection conn = ConnectionHelper.getConnection("oracle");
        String query = "SELECT SUPPLIERID FROM SUPPLIER";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Supplier supplier = new Supplier();
                supplier.setSupplierId(rs.getString("SUPPLIERID"));
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }
}
