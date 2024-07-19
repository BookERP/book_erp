package main.java.com.bookstore.dao;

import main.java.com.bookstore.model.Product;
import main.java.com.bookstore.model.User;
import main.java.com.bookstore.util.ConnectionHelper;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
	public User selectMyUser(String userID) throws SQLException {
		List<Product> myuser = new ArrayList<>();
		User user = null;
		Connection conn = ConnectionHelper.getConnection("oracle");
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
