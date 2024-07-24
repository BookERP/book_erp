package src.main.java.com.bookstore.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import src.main.java.com.bookstore.dao.CustomerDAO;
import src.main.java.com.bookstore.dao.OrderDAO;
import src.main.java.com.bookstore.dao.PaymentDAO;
import src.main.java.com.bookstore.dao.ProductDAO;
import src.main.java.com.bookstore.model.Customer;
import src.main.java.com.bookstore.model.Payment;
import src.main.java.com.bookstore.model.Product;

public class OrderPanel extends JFrame {
    private JComboBox<String> comboCustomerName, comboBookId, comboPaymentMethod;
    private JTextField txtBookName, txtPrice, txtStatus;
    private JTable orderTable;
    private DefaultTableModel tableModel;
    private CustomerDAO customerDAO;
    private OrderDAO orderDAO;
    private PaymentDAO paymentDAO;
    private ProductDAO productDAO;

    public OrderPanel() {
        customerDAO = new CustomerDAO();
        orderDAO = new OrderDAO();
        paymentDAO = new PaymentDAO();
        productDAO = new ProductDAO();
        setTitle("주문 관리");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(8, 2));
        inputPanel.add(new JLabel("고객 이름:"));
        comboCustomerName = new JComboBox<>();
        inputPanel.add(comboCustomerName);
//        loadCustomers();

        inputPanel.add(new JLabel("제품 고유번호"));
        comboBookId = new JComboBox<>();
        loadBooks(); // 제품 고유번호를 선택하면 도서명과 가격이 나온다.
        comboBookId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadBookDetails();
            }
        });
        inputPanel.add(comboBookId);

        inputPanel.add(new JLabel("도서명"));
        txtBookName = new JTextField();
        txtBookName.setEditable(false);
        inputPanel.add(txtBookName);

        inputPanel.add(new JLabel("가격"));
        txtPrice = new JTextField();
        txtPrice.setEditable(false);
        inputPanel.add(txtPrice);

        inputPanel.add(new JLabel("상태"));
        txtStatus = new JTextField();
        inputPanel.add(txtStatus);

        inputPanel.add(new JLabel("결제수단:"));
        comboPaymentMethod = new JComboBox<>();
        inputPanel.add(comboPaymentMethod);
        loadPayments();

        add(inputPanel, BorderLayout.NORTH);
        
        tableModel = new DefaultTableModel(new String[]{"주문번호", "고객이름", "상품 고유번호","도서명", "주문일", "배송일", "상태", "가격", "결제수단"}, 0);
        orderTable = new JTable(tableModel);
        add(new JScrollPane(orderTable), BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("추가");
        JButton btnUpdate = new JButton("수정");
        JButton btnDelete = new JButton("삭제");
        JButton btnSearch = new JButton("검색");
        JButton btnSelectAll = new JButton("전체 목록 보기");
        
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnSelectAll);
        
        add(buttonPanel, BorderLayout.SOUTH);
        
        btnSelectAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	loadOrders();
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOrders();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateOrders();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteOrders();
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchOrders();
            }
        });
        
        loadOrders();
    }

    protected void addOrders() {
		// TODO Auto-generated method stub
		
	}

	private void loadPayments() {
        List<Payment> payments = paymentDAO.getAllPayments();
        for (Payment payment : payments) {
            comboPaymentMethod.addItem(payment.getMethod());
        }
    }

    private void loadBooks() {
        List<Product> books = productDAO.getAllProducts();
        for (Product book : books) {
            comboBookId.addItem(book.getProductId());
        }
    }

    private void loadBookDetails() {
        String bookId = (String) comboBookId.getSelectedItem();
        if (bookId != null) {
            Product book = productDAO.getProductById(bookId);
            if (book != null) {
                txtBookName.setText(book.getName());
                txtPrice.setText(String.valueOf(book.getPrice()));
            }
        }
    }

//    private void loadCustomers() {
//        List<Customer> customers = customerDAO.getAllCustomers();
//        for (Customer customer : customers) {
//            comboCustomerName.addItem(customer.getName());
//        }
//    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new OrderPanel().setVisible(true);
            }
        });
    }
}


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
