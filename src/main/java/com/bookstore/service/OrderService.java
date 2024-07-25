package src.main.java.com.bookstore.service;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import src.main.java.com.bookstore.model.Order;
import src.main.java.com.bookstore.model.Payment;
import src.main.java.com.bookstore.model.Product;

public class OrderService extends JFrame {
	private JComboBox<String> comboStatus, comboCustomerId, comboBookId, comboPaymentMethod;
	private JTextField txtstatus, txtCustomerName, txtAddress, txtBookName, txtPrice;
	private JTable orderTable;
	private DefaultTableModel tableModel;
	private CustomerDAO customerDAO;
	private OrderDAO orderDAO;
	private PaymentDAO paymentDAO;
	private ProductDAO productDAO;

	public OrderService() {
		customerDAO = new CustomerDAO();
		orderDAO = new OrderDAO();
		paymentDAO = new PaymentDAO();
		productDAO = new ProductDAO();
		setTitle("주문 관리");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		JPanel inputPanel = new JPanel(new GridLayout(6, 2));

		inputPanel.add(new JLabel("상태(결제중 or 결제완료)"));
		txtstatus = new JTextField();
		inputPanel.add(txtstatus);

		inputPanel.add(new JLabel("고객 번호:"));
		comboCustomerId = new JComboBox<>();
		loadCustomers(); // 고객번호 선택하면 이름과 주소가 나오게 합시다.
		comboCustomerId.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadCustomerDetails();
			}
		});
		inputPanel.add(comboCustomerId);

		inputPanel.add(new JLabel("이름"));
		txtCustomerName = new JTextField();
		txtCustomerName.setEditable(false);
		inputPanel.add(txtCustomerName);

//		inputPanel.add(new JLabel("주소"));
//		txtAddress = new JTextField();
//		txtAddress.setEditable(false);
//		inputPanel.add(txtAddress);

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

		add(inputPanel, BorderLayout.NORTH);

		tableModel = new DefaultTableModel(
				new String[] { "주문번호", "주문일", "배송일", "상태", "고객번호", "이름", "도서번호", "도서명", "가격" }, 0);
		orderTable = new JTable(tableModel);
		add(new JScrollPane(orderTable), BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		JButton btnAdd = new JButton("추가");
//		JButton btnUpdate = new JButton("수정");
		JButton btnDelete = new JButton("삭제");
//		JButton btnSearch = new JButton("검색");
		JButton btnSelectAll = new JButton("전체 목록 보기");

		buttonPanel.add(btnAdd);
//		buttonPanel.add(btnUpdate);
		buttonPanel.add(btnDelete);
//		buttonPanel.add(btnSearch);
		buttonPanel.add(btnSelectAll);

		add(buttonPanel, BorderLayout.SOUTH);

		btnSelectAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadOrders();
			}
		});
//
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addOrders();
			}
		});
//
//        btnUpdate.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                updateOrders();
//            }
//        });
//
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteOrders();
			}
		});
//
//        btnSearch.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                searchOrders();
//            }
//        });
//        
		loadOrders();
	}

	protected void deleteOrders() {
		int selectedRow = orderTable.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "삭제할 주문을 선택하세요.");
			return;
		}

		String orderId = (String) tableModel.getValueAt(selectedRow, 0);
		int confirm = JOptionPane.showConfirmDialog(this, "정말로 이 주문을 삭제하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			orderDAO.deleteOrder(orderId);
			tableModel.removeRow(selectedRow);
			JOptionPane.showMessageDialog(this, "주문이 성공적으로 삭제되었습니다.");
		}
	}

	private void loadStatus() {
		List<String> statuses = orderDAO.getAllStatuses();
		for (String status : statuses) {
			comboStatus.addItem(status);
		}
	}

	private void loadCustomers() {
		List<Customer> customers = customerDAO.getAllCustomers();
		for (Customer customer : customers) {
			comboCustomerId.addItem(customer.getCustomerId());
		}
	}

	private void loadCustomerDetails() {
		String customerId = (String) comboCustomerId.getSelectedItem();
		if (customerId != null) {
			Customer customer = customerDAO.getCustomerByID(customerId);
			if (customer != null) {
				txtCustomerName.setText(customer.getCName());
//				txtAddress.setText(customer.getCAddress());
			}
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

	private void loadOrders() {
		List<Order> orders = orderDAO.getAllOrders();
		tableModel.setRowCount(0); // 기존 행을 모두 삭제합니다.

		for (Order order : orders) {
			String orderDate = (order.getOrderDate() != null) ? order.getOrderDate().toString() : "N/A";
			String shippingDate = (order.getShippingDate() != null) ? order.getShippingDate().toString() : "N/A";

			if (order.getProductId() != null && !order.getProductId().isEmpty()) {
				Product book = productDAO.getProductById(order.getProductId());
				String bookId = (book != null) ? book.getProductId() : "N/A";
				String bookName = (book != null) ? book.getName() : "N/A";
				double price = (book != null) ? book.getPrice() : 0.0;

				System.out.println(bookId);
				System.out.println(bookName);
				System.out.println(price);

				tableModel.addRow(new Object[] { order.getOrderID(), orderDate, shippingDate, order.getStatus(),
						order.getCustomerId(), order.getCustomerName(), bookId, bookName, price });
			} else {
				tableModel.addRow(new Object[] { order.getOrderID(), orderDate, shippingDate, order.getStatus(),
						order.getCustomerId(), order.getCustomerName(), "N/A", "N/A", 0.0 });
			}

//	    	Product book = productDAO.getProductById(order.getProductId());
//	        String bookId = (book != null) ? book.getProductId() : "N/A";
//	        String bookName = (book != null) ? book.getName() : "N/A";
//	        double price = (book != null) ? book.getPrice() : 0.0;
//	        
//	        System.out.println(bookId);
//	        System.out.println(bookName);
//	        System.out.println(price);
//
//
//	        tableModel.addRow(new Object[] {
//	            order.getOrderID(),
//	            orderDate,
//	            shippingDate,
//	            order.getStatus(),
//	            order.getCustomerId(),
//	            order.getCustomerName(),
//	            bookId,
//	            bookName,
//	            price
//	        });
		}
	}

	private void addOrders() {
		LocalDateTime now = LocalDateTime.now();
		Date orderDate = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
		Date shippingDate = Date.from(now.plusDays(3).atZone(ZoneId.systemDefault()).toInstant());

		String status = txtstatus.getText();
		if (status == null || status.isBlank()) {
			JOptionPane.showMessageDialog(this, "상태를 입력하세요.");
			return;
		}
		if (!status.equals("결제중") && !status.equals("결제완료")) {
			JOptionPane.showMessageDialog(this, "상태를 결제중 또는 결제완료로 입력해 주세요.");
			return;
		}

		String customerId = (String) comboCustomerId.getSelectedItem();
		if (customerId == null || customerId.isEmpty()) {
			JOptionPane.showMessageDialog(this, "고객 번호를 선택하세요.");
			return;
		}
		Customer customer = customerDAO.getCustomerByID(customerId);
		if (customer == null) {
			JOptionPane.showMessageDialog(this, "고객 정보를 찾을 수 없습니다.");
			return;
		}
		String customerName = customer.getCName();
		String customerAddress = customer.getCAddress();

		String bookId = (String) comboBookId.getSelectedItem();
		if (bookId == null || bookId.isEmpty()) {
			JOptionPane.showMessageDialog(this, "제품 고유번호를 선택하세요.");
			return;
		}
		Product book = productDAO.getProductById(bookId);
		if (book == null) {
			JOptionPane.showMessageDialog(this, "도서 정보를 찾을 수 없습니다.");
			return;
		}
		String bookName = book.getName();
		double price = book.getPrice();

		Order newOrder = new Order();
		newOrder.setOrderID(orderDAO.getNextOrderId());
		newOrder.setOrderDate(orderDate);
		newOrder.setShippingDate(shippingDate);
		newOrder.setStatus(status);
		newOrder.setCustomerId(customerId);
		newOrder.setCustomerName(customerName);
//		newOrder.setCustomerAddress(customerAddress);
		newOrder.setProductId(bookId); // Add the product ID to the order
		newOrder.setProductName(bookName);
		newOrder.setProductPrice(price);

		orderDAO.addOrder(newOrder);
		loadOrders();
	}

	private void loadPayments() {
		List<Payment> payments = paymentDAO.getAllPayments();
		for (Payment payment : payments) {
			comboPaymentMethod.addItem(payment.getMethod());
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new OrderService().setVisible(true);
			}
		});
	}
}
