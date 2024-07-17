<<<<<<< HEAD
package src.main.java.com.bookstore.dao;

import src.main.java.com.bookstore.model.Supplier;
import src.main.java.com.bookstore.util.ConnectionHelper;
=======
package main.java.com.bookstore.dao;

import main.java.com.bookstore.model.Supplier;
import main.java.com.bookstore.util.ConnectionHelper;
>>>>>>> 1dfa6bbc17224b955e85a46774d317d7b18704ab

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
