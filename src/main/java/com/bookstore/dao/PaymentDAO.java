package src.main.java.com.bookstore.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import src.main.java.com.bookstore.model.Payment;
import src.main.java.com.bookstore.util.ConnectionHelper;

public class PaymentDAO {
	private Connection conn;

    public PaymentDAO() {
        conn = ConnectionHelper.getConnection();
    }
    
    public List<Payment> getAllPayments() {
    	List<Payment> payments = new ArrayList<>();
    	String query = "SELECT PAYMENTID, METHOD FROM PAYMENT";
//    	String query = "SELECT PaymentID FROM Payment";
    	try (
    		 Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
    		while (rs.next()) {
    			Payment payment = new Payment();
    			payment.setPaymentId(rs.getString("PAYMENTID"));
    			payment.setMethod(rs.getString("METHOD"));
    			payments.add(payment);
//    			System.out.println("Loaded payment method: " + payment.getMethod());
    		}
    	} catch (Exception e) {
            e.printStackTrace();
        }
    	return payments;
    }
}
