package src.main.java.com.bookstore.dao;

import src.main.java.com.bookstore.model.Product;
import src.main.java.com.bookstore.model.User;
import src.main.java.com.bookstore.util.ConnectionHelper;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
	
	private Connection conn;

    public CustomerDAO() {
        conn = ConnectionHelper.getConnection();
    }
	
	public User selectMyUser(String userID) throws SQLException {
		List<Product> myuser = new ArrayList<>();
		User user = null;
		String query = "SELECT CUSTOMERID, NAME, PHONE, EMAIL, ADRESS, RDATE FROM Customer where CustomerID = ?";
		// ?의 경우 로그인할 때 저장된 아이디를 가져와야 한다/
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, userID);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String id = rs.getString("CUSTOMERID");
				String name = rs.getString("NAME");
				String phone = rs.getString("PHONE");
				String email = rs.getString("EMAIL");
				String address = rs.getString("ADRESS");
				user = new User(id, name, phone, email, address);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) conn.close();
		}
		return user;
	}
}
