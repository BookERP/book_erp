package main.java.com.bookstore.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.com.bookstore.model.Customer;
import main.java.com.bookstore.model.Order;
import main.java.com.bookstore.util.ConnectionHelper;

public class OrderDAO {
	private Connection conn;

    public OrderDAO() {
        conn = ConnectionHelper.getConnection();
    }
    
    public List<String> getAllStatuses() {
    	List<String> statuses = new ArrayList<>();
    	String query = "SELECT DISTINCT status FROM \"ORDER\"";
    	try (
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    statuses.add(rs.getString("status"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    	return statuses;
    }

	public String getNextOrderId() {
		String query = "SELECT MAX(ORDERID) AS MAX_ID FROM \"ORDER\"";
		try (
	         PreparedStatement stmt = conn.prepareStatement(query);
	         ResultSet rs = stmt.executeQuery()) {
			if(rs.next()) {
				String maxId = rs.getString("MAX_ID");
				if(maxId != null) {
					String[] parts = maxId.split("-");
                    if (parts.length == 2) {
                        int idNumber = Integer.parseInt(parts[1]);
                        return String.format("ORDR-%04d", idNumber + 1);
                    }
				}
			}
			
		} catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
		return "ORDR-0001";
	}

	public void addOrder(Order newOrder) {
		String orderQuery = "INSERT INTO \"ORDER\" (ORDERID, ORDERDATE, SHIPPINGDATE, STATUS, CID) VALUES (?, ?, ?, ?, ?)";

	    try (PreparedStatement orderPst = conn.prepareStatement(orderQuery)) {
	        conn.setAutoCommit(false);

	        // 고객 정보 확인
	        String customerID = newOrder.getCustomerId();
	        Customer existingCustomer = getCustomerByID(customerID);
	        if (existingCustomer == null) {
	            throw new SQLException("Customer not found");
	        }

	        // Order 테이블에 데이터 삽입
	        orderPst.setString(1, newOrder.getOrderID());
	        orderPst.setDate(2, new java.sql.Date(newOrder.getOrderDate().getTime()));
	        orderPst.setDate(3, new java.sql.Date(newOrder.getShippingDate().getTime()));
	        orderPst.setString(4, newOrder.getStatus());
	        orderPst.setString(5, customerID);
	        orderPst.executeUpdate();

	        conn.commit();
	    } catch (SQLException e) {
	        try {
	            conn.rollback(); // 트랜잭션 롤백
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        e.printStackTrace();
	    } finally {
	        try {
	            conn.setAutoCommit(true); // 자동 커밋 모드로 전환
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	private Customer getCustomerByID(String customerID) {
	    String query = "SELECT * FROM \"CUSTOMER\" WHERE CUSTOMERID = ?";
	    try (PreparedStatement pst = conn.prepareStatement(query)) {
	        pst.setString(1, customerID);
	        try (ResultSet rs = pst.executeQuery()) {
	            if (rs.next()) {
	                Customer customer = new Customer();
	                customer.setCustomerId(rs.getString("CUSTOMERID"));
	                customer.setCName(rs.getString("CNAME"));
	                customer.setCAddress(rs.getString("CADDRESS"));
	                return customer;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	public List<Order> getAllOrders() {
		List<Order> orders = new ArrayList<>();
	    String query = "SELECT o.ORDERID, o.ORDERDATE, o.SHIPPINGDATE, o.STATUS, c.CUSTOMERID, c.CNAME, c.CADDRESS " +
	                   "FROM \"ORDER\" o " +
	                   "JOIN \"CUSTOMER\" c ON o.CID = c.CUSTOMERID";
	    try (Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery(query)) {
	           while (rs.next()) {
	               Order order = new Order();
	               order.setOrderID(rs.getString("ORDERID"));
	               order.setOrderDate(rs.getDate("ORDERDATE"));
	               order.setShippingDate(rs.getDate("SHIPPINGDATE"));
	               order.setStatus(rs.getString("STATUS"));
	               order.setCustomerId(rs.getString("CUSTOMERID"));
	               order.setCustomerName(rs.getString("CNAME"));
	               order.setCustomerAddress(rs.getString("CADDRESS"));
	               orders.add(order);
	           }
	       } catch (Exception e) {
	           e.printStackTrace();
	       }
	       return orders;
	}

	public void deleteOrder(String orderId) {
		String query = "DELETE FROM \"ORDER\" WHERE ORDERID = ?";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, orderId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}