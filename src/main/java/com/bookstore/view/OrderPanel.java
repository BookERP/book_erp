package main.java.com.bookstore.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import main.java.com.bookstore.dao.CustomerDAO;
import main.java.com.bookstore.dao.OrderDAO;
import main.java.com.bookstore.dao.PaymentDAO;
import main.java.com.bookstore.dao.ProductDAO;
import main.java.com.bookstore.model.Customer;
import main.java.com.bookstore.model.Order;
import main.java.com.bookstore.model.Product;

public class OrderPanel extends JFrame {
	private JComboBox<String> comboCustomerId, comboBookId;
	private JTextField txtstatus, txtCustomerName, txtAddress, txtBookName, txtPrice;
	private JTable orderTable, productTable, combinedTable;
	private DefaultTableModel orderTableModel, productTableModel, combinedTableModel;
	private CustomerDAO customerDAO;
	private OrderDAO orderDAO;
	private PaymentDAO paymentDAO;
	private ProductDAO productDAO;

	public OrderPanel() {
		customerDAO = new CustomerDAO();
		orderDAO = new OrderDAO();
		productDAO = new ProductDAO();
		setTitle("주문 관리");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		JPanel inputPanel = new JPanel(new GridLayout(7, 2));

		inputPanel.add(new JLabel("상태(결제중, 결제완료 둘 중 하나를 입력해주세요.)"));
		txtstatus = new JTextField();
		inputPanel.add(txtstatus);

		inputPanel.add(new JLabel("고객 번호:"));
		comboCustomerId = new JComboBox<>();
		loadCustomers();
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

		inputPanel.add(new JLabel("주소"));
		txtAddress = new JTextField();
		txtAddress.setEditable(false);
		inputPanel.add(txtAddress);

		inputPanel.add(new JLabel("제품 고유번호"));
		comboBookId = new JComboBox<>();
		loadBooks();
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

		combinedTableModel = new DefaultTableModel(
				new String[] { "주문번호", "주문일", "배송일", "상태", "고객번호", "이름", "주소", "제품번호", "도서명", "가격" }, 0);
		combinedTable = new JTable(combinedTableModel);
		add(new JScrollPane(combinedTable), BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		JButton btnAdd = new JButton("추가");
		JButton btnDelete = new JButton("삭제");
		JButton btnSelectAll = new JButton("전체 목록 보기");

		buttonPanel.add(btnAdd);
		buttonPanel.add(btnDelete);
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

		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteOrders();
			}
		});

		loadOrders();
	}

	private void deleteOrders() {
		int selectedRow = combinedTable.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "삭제할 주문을 선택하세요.");
			return;
		}

		String orderId = (String) combinedTableModel.getValueAt(selectedRow, 0);
		int confirm = JOptionPane.showConfirmDialog(this, "정말로 이 주문을 삭제하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			orderDAO.deleteOrder(orderId);
			combinedTableModel.removeRow(selectedRow);
			JOptionPane.showMessageDialog(this, "주문이 성공적으로 삭제되었습니다.");
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
				txtAddress.setText(customer.getCAddress());
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
				txtBookName.setText(book.getPname());
				txtPrice.setText(String.valueOf(book.getPrice()));
			}
		}
	}

	private void addOrders() {
		LocalDateTime now = LocalDateTime.now();
		Date orderDate = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
		Date shippingDate = Date.from(now.plusDays(3).atZone(ZoneId.systemDefault()).toInstant());

		String status = txtstatus.getText();
		if (status == null || status.isEmpty()) {
			JOptionPane.showMessageDialog(this, "상태를 입력하세요.");
			return;
		}
		if (!status.equals("결제중") && !status.equals("결제완료")) {
			JOptionPane.showMessageDialog(this, "상태는 '결제중' 또는 '결제완료'만 가능합니다.");
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

		String productId = (String) comboBookId.getSelectedItem();
		if (productId == null || productId.isEmpty()) {
			JOptionPane.showMessageDialog(this, "제품 고유번호를 선택하세요.");
			return;
		}
		Product product = productDAO.getProductById(productId);
		if (product == null) {
			JOptionPane.showMessageDialog(this, "제품 정보를 찾을 수 없습니다.");
			return;
		}
		String productName = product.getPname();
		double price = product.getPrice();

		Order newOrder = new Order();
		newOrder.setOrderID(orderDAO.getNextOrderId());
		newOrder.setOrderDate(orderDate);
		newOrder.setShippingDate(shippingDate);
		newOrder.setStatus(status);
		newOrder.setCustomerId(customerId);
		newOrder.setCustomerName(customerName);
		newOrder.setCustomerAddress(customerAddress);
		newOrder.setProductId(productId);
		newOrder.setProductName(productName);
		newOrder.setProductPrice(price);

//		System.out.println("New Order Added: " + newOrder); // 디버깅 메시지 추가

		orderDAO.addOrder(newOrder);
		loadOrders();

		JOptionPane.showMessageDialog(this, "주문이 성공적으로 추가되었습니다.");
	}

	private void loadOrders() {
		List<Order> orders = orderDAO.getAllOrders();
		combinedTableModel.setRowCount(0);
		for (Order order : orders) {
			//System.out.println("Loading Order: " + order); // 디버깅 메시지 추가
			combinedTableModel.addRow(new Object[] {
					order.getOrderID(),
					order.getOrderDate() != null ? order.getOrderDate().toString() : "N/A",
					order.getShippingDate() != null ? order.getShippingDate().toString() : "N/A",
					order.getStatus(),
					order.getCustomerId(),
					order.getCustomerName(),
					order.getCustomerAddress(),
					order.getProductId(),
					order.getProductName(),
					order.getProductPrice()
			});
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new OrderPanel().setVisible(true);
			}
		});
	}
}
