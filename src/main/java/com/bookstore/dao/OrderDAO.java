package src.main.java.com.bookstore.dao;

public class OrderDAO {
	
}

//package src.main.java.com.bookstore.dao;
//
//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import src.main.java.com.bookstore.model.Order;
//import src.main.java.com.bookstore.util.ConnectionHelper;
//
//public class OrderDAO {
//	
//	private Connection conn;
//	
//	public OrderDAO() {
//		conn = ConnectionHelper.getConnection();
//	}
//
//	public void addOrder(Order order) {
//		String query = "INSERT INTO `ORDER` (ORDERID, ORDERDATE, SHIPPINGDATE, STATUS, AMOUNT, CID) VALUES (?, ?, ?, ?, ?, ?)";
//		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
//			pstmt.setString(1, order.getOrderID());
//			pstmt.setDate(2, (Date) order.getOrderDate());
//			pstmt.setDate(3, (Date) order.getShippingDate());
//			pstmt.setString(4, order.getStatus());
//			pstmt.setDouble(5, order.getAmount());
//			pstmt.setString(6, order.getCID());
//			pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void updateOrder() {
//		String query = "UPDATE `ORDER` SET ";
//	}
//
//	public void deleteOrder() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public String getNextProductID() {
//		String query = "SELECT MAX(ORDERID) AS MAX_ID FROM `ORDER`";
//		try(
//			PreparedStatement stmt = conn.prepareStatement(query);
//			ResultSet rs = stmt.executeQuery()) {
//			if(rs.next()) {
//				String maxId = rs.getString("MAX_ID");
//				if (maxId != null) {
//					String[] parts = maxId.split("-");
//					int idNumber = Integer.parseInt(parts[1]);
//					return String.format("ORDER-%04d", idNumber + 1);
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return "ORDER-0001";
//	}
//
//}
