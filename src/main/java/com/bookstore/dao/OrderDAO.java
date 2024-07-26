package src.main.java.com.bookstore.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.main.java.com.bookstore.model.Customer;
import src.main.java.com.bookstore.model.Order;
import src.main.java.com.bookstore.model.Product;
import src.main.java.com.bookstore.util.ConnectionHelper;
import src.main.java.com.bookstore.dao.CustomerDAO;
import src.main.java.com.bookstore.dao.ProductDAO;

public class OrderDAO {
	private CustomerDAO customerDAO;
    private ProductDAO productDAO;
	private Connection conn;

	public OrderDAO() {
		conn = ConnectionHelper.getConnection();
		customerDAO = new CustomerDAO();
        productDAO = new ProductDAO();
	}

	public List<String> getAllStatuses() {
		List<String> statuses = new ArrayList<>();
		String query = "SELECT DISTINCT status FROM \"ORDER\"";
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
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
		try (PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
			if (rs.next()) {
				String maxId = rs.getString("MAX_ID");
				if (maxId != null) {
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
			Customer existingCustomer = customerDAO.getCustomerByID(customerID);
			if (existingCustomer == null) {
				throw new SQLException("Customer not found");
			}

			// 상품 정보 확인
			String productID = newOrder.getProductId();
			Product existingProduct = productDAO.getProductById(productID);
			if (existingProduct == null) {
				throw new SQLException("Product not found");
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

	private Product getProductByID(String productID) {
		Product product = null;
		String query = "SELECT * FROM \"PRODUCT\" WHERE PRODUCTID = ?";
		try (PreparedStatement pst = conn.prepareStatement(query)) {
			pst.setString(1, productID);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					product = new Product();
					product.setProductId(rs.getString("PRODUCTID"));
					product.setName(rs.getString("PNAME"));
					product.setPrice(rs.getDouble("PRICE"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	private Customer getCustomerByID(String customerID) {
		Customer customer = null;
		String query = "SELECT * FROM \"CUSTOMER\" WHERE CUSTOMERID = ?";
		try (PreparedStatement pst = conn.prepareStatement(query)) {
			pst.setString(1, customerID);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					customer = new Customer();
					customer.setCustomerId(rs.getString("CUSTOMERID"));
					customer.setCName(rs.getString("CNAME"));
					customer.setCAddress(rs.getString("CADDRESS"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}

	public List<Order> getAllOrders() {
		List<Order> orders = new ArrayList<>();																									// 필터링
		String orderQuery = "SELECT o.ORDERID, o.ORDERDATE, o.SHIPPINGDATE, o.STATUS, "
				+ "c.CUSTOMERID, c.CNAME, c.CADDRESS " 
				+ "FROM \"ORDER\" o "
				+ "JOIN \"CUSTOMER\" c ON o.CID = c.CUSTOMERID";
		
		try (Statement orderStmt = conn.createStatement();
		     ResultSet orderRs = orderStmt.executeQuery(orderQuery)) {
			while (orderRs.next()) {
	            Order order = new Order();
	            order.setOrderID(orderRs.getString("ORDERID"));
	            order.setOrderDate(orderRs.getDate("ORDERDATE"));
	            order.setShippingDate(orderRs.getDate("SHIPPINGDATE"));
	            order.setStatus(orderRs.getString("STATUS"));
	            order.setCustomerId(orderRs.getString("CUSTOMERID"));
	            order.setCustomerName(orderRs.getString("CNAME"));
	            order.setCustomerAddress(orderRs.getString("CADDRESS"));
	            
//	            Product product = getProductForOrder(order.getOrderID());
//	            if (product != null) {
//	                order.setProductId(product.getProductId());
//	                order.setProductName(product.getName());
//	                order.setProductPrice(product.getPrice());
//	            }
	            
	            orders.add(order);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return orders;
	}
	
	private Product getProductForOrder(String orderID) {
		Product product = null;
        String productQuery = "SELECT p.PRODUCTID, p.PNAME, p.PRICE "
                + "FROM \"PRODUCT\" p "
                + "WHERE p.PRODUCTID = (SELECT PRODUCTID FROM \"ORDER_PRODUCT\" WHERE ORDERID = ?)";
        
        try (PreparedStatement productStmt = conn.prepareStatement(productQuery)) {
            productStmt.setString(1, orderID);
            try (ResultSet productRs = productStmt.executeQuery()) {
                if (productRs.next()) {
                    product = new Product();
                    product.setProductId(productRs.getString("PRODUCTID"));
                    product.setName(productRs.getString("PNAME"));
                    product.setPrice(productRs.getDouble("PRICE"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
	}

	public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String productQuery = "SELECT p.PRODUCTID, p.PNAME, p.PRICE FROM \"PRODUCT\" p";
        try (Statement productStmt = conn.createStatement();
             ResultSet productRs = productStmt.executeQuery(productQuery)) {
            
            while (productRs.next()) {
                Product product = new Product();
                product.setProductId(productRs.getString("PRODUCTID"));
                product.setName(productRs.getString("PNAME"));
                product.setPrice(productRs.getDouble("PRICE"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
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
