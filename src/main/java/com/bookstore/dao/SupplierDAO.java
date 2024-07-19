package main.java.com.bookstore.dao;

import main.java.com.bookstore.model.Supplier;
import main.java.com.bookstore.util.ConnectionHelper;

import java.sql.Connection;
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
        String query = "SELECT SUPPLIERID, NAME FROM SUPPLIER";
        try (
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Supplier supplier = new Supplier();
                supplier.setSupplierId(rs.getString("SUPPLIERID"));
                supplier.setName(rs.getString("NAME"));
                suppliers.add(supplier);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return suppliers;
    }
}
