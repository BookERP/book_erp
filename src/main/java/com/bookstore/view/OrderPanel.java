//package src.main.java.com.bookstore.view;
//
//import java.awt.BorderLayout;
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//
//import src.main.java.com.bookstore.dao.OrderDAO;
//import src.main.java.com.bookstore.model.Order;
//
//public class OrderPanel extends JFrame {
//	private JTextField orderIdField, orderDateField, 
//						shippingDateField, statusField, 
//						amountField, customerIdField;
//	private JButton addButton, updateButton, deleteButton;
//	private OrderDAO orderDAO;
//	
//	public OrderPanel() {
//		orderDAO = new OrderDAO();
//		setTitle("주문 관리");
//        setSize(800, 600);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setLayout(new BorderLayout());
//        
//        JPanel panel = new JPanel();
//        panel.setLayout(new GridLayout(8, 2));
//        
//        panel.add(new JLabel("주문 ID:"));
//        orderIdField = new JTextField();
//        panel.add(orderIdField);
//
//        panel.add(new JLabel("주문 일자:"));
//        orderDateField = new JTextField();
//        panel.add(orderDateField);
//
//        panel.add(new JLabel("배송 일자:"));
//        shippingDateField = new JTextField();
//        panel.add(shippingDateField);
//
//        panel.add(new JLabel("상태:"));
//        statusField = new JTextField();
//        panel.add(statusField);
//
//        panel.add(new JLabel("주문액:"));
//        amountField = new JTextField();
//        panel.add(amountField);
//
//        panel.add(new JLabel("고객 ID:"));
//        customerIdField = new JTextField();
//        panel.add(customerIdField);
//
//        addButton = new JButton("주문추가");
//        panel.add(addButton);
//        updateButton = new JButton("주문수정");
//        panel.add(updateButton);
//        deleteButton = new JButton("주문삭제");
//        panel.add(deleteButton);
//        
//        add(panel, BorderLayout.WEST);
//        
//        addButton.addActionListener(new ActionListener() {
//        	@Override
//            public void actionPerformed(ActionEvent e) {
//                addOrder();
//            }
//        });
//
//        updateButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//            	orderDAO.updateOrder();
//            }
//        });
//
//        deleteButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//            	orderDAO.deleteOrder();
//            }
//        });
//	}
//	
//	private void addOrder() {
//		String orderId = orderIdField.getText();
//		String orderDate = orderDateField.getText();
//        String shippingDate = shippingDateField.getText();
//        String status = statusField.getText();
//        String amount = amountField.getText();
//        String customerId = customerIdField.getText();
//        
//        Order order = new Order();
//        order.setOrderID(orderDAO.getNextProductID());
//        order.setOrderDate(txt);
//	}
//	
//	private void updateOrder() {
//        String orderId = orderIdField.getText();
//        String orderDate = orderDateField.getText();
//        String shippingDate = shippingDateField.getText();
//        String status = statusField.getText();
//        String amount = amountField.getText();
//        String customerId = customerIdField.getText();
//
//        try {
//            orderDAO.updateOrder(orderId, orderDate, shippingDate, status, amount, customerId);
//            JOptionPane.showMessageDialog(this, "Order updated successfully!");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Error updating order: " + e.getMessage());
//        }
//    }
//	
//	private void deleteOrder() {
//        String orderId = orderIdField.getText();
//
//        try {
//            orderDAO.deleteOrder(orderId);
//            JOptionPane.showMessageDialog(this, "Order deleted successfully!");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Error deleting order: " + e.getMessage());
//        }
//    }
//
//	public static void main(String[] args) {
//		OrderPanel panel = new OrderPanel();
//		panel.setVisible(true);
//	}
//}
