package main.java.com.bookstore.view;

import main.java.com.bookstore.DAO.CustomerDAO;

import main.java.com.bookstore.model.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CustomerPanel extends JFrame {
	private JTextField txtCustomerId, txtName, txtPhone, txtEmail, txtAddress, txtRdate , txtCustomerCpw;
	private JComboBox<String> combocustomerId;
	private JTable productTable;
    private DefaultTableModel tableModel;
    private CustomerDAO customerDAO;
    
    
    public CustomerPanel() {
    	setTitle("고객 관리");
    	setSize(800, 600);
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        JPanel inputPanel = new JPanel(new GridLayout(7, 0));
        
        inputPanel.add(new JLabel("이   름:"));
        combocustomerId = new JComboBox<>();
        inputPanel.add(combocustomerId);
        loadCustomers();

        inputPanel.add(new JLabel("연 락 처:"));
        txtPhone = new JTextField();
        inputPanel.add(txtPhone);

        inputPanel.add(new JLabel("이 메 일:"));
        txtEmail = new JTextField();
        inputPanel.add(txtEmail);

        inputPanel.add(new JLabel("주   소:"));
        txtAddress = new JTextField();
        inputPanel.add(txtAddress);

        inputPanel.add(new JLabel("가입일자:"));
        txtRdate = new JTextField();
        inputPanel.add(txtRdate);

        inputPanel.add(new JLabel("고객 ID:"));
        txtCustomerId = new JTextField();
        inputPanel.add(txtCustomerId);

        inputPanel.add(new JLabel("고객 PWD:"));
        txtCustomerCpw = new JTextField();
        inputPanel.add(txtCustomerCpw);

        add(inputPanel, BorderLayout.SOUTH);

        tableModel = new DefaultTableModel(new String[]{"아이디", "비밀번호", "이름", "연락처", "이메일", "주소", "가입일자"}, 0);
        productTable = new JTable(tableModel);
        add(new JScrollPane(productTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton btnSelectAll = new JButton("전체 목록 보기");
        JButton btnSearch = new JButton("검색");
        JButton btnDelete = new JButton("삭제");
        JButton btnUpdate = new JButton("수정");
        
        buttonPanel.add(btnSelectAll);
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnUpdate);
        
        add(buttonPanel, BorderLayout.SOUTH);
        
        btnSelectAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	loadCustomers();
            }
        });


        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCustomer();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCustomer();
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchCustomer();
            }
        });

        loadCustomers();
    }

    private void loadCustomers() {
        List<Customer> customers = customerDAO.getAllCustomers();
        tableModel.setRowCount(0); // Clear existing data
        for (Customer customer : customers) {
            tableModel.addRow(new Object[]{
            	customer.getCustomerId(),
            	customer.getName(),
            	customer.getPhone(),
            	customer.getEmail(),
            	customer.getAddress(),
            	customer.getRDate(),
            	customer.getCustomerCpw()
            });
        }
    }

    private void updateCustomer() {
        String customerId = JOptionPane.showInputDialog(this, "수정할 고객의 아이디(ID)를 입력하세요:");
        if (customerId == null || customerId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "고객 아이디(ID)를 입력하세요.");
            return;
        }

        String name = txtName.getText();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "이름을 입력하세요.");
            return;
        }
        String Phone = txtPhone.getText();
        if (Phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "연락처를 입력하세요.");
            return;
        }
        String Email = txtEmail.getText();
        if (Email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "이메일를 입력하세요.");
            return;
        }
        String Address = txtAddress.getText();
        if (Address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "주소를 입력하세요.");
            return;
        }
//        String Rdate = txtRdate.getText();
//        if (Rdate.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "재고 수를 입력하세요.");
//            return;
//        }
        String Customercpw = txtCustomerCpw.getText();
        if (Customercpw.isEmpty()) {
            JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요.");
            return;
        }
        
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setName(txtName.getText());
        customer.setPhone(txtPhone.getText());
        customer.setEmail(txtEmail.getText());
        customer.setAddress(txtAddress.getText());
//        customer.setRDate(txtRdate.getText());
        customer.setCustomerCpw(txtCustomerCpw.getText());
        customerDAO.updateCustomer(customer);
        loadCustomers();
    }

    private void deleteCustomer() {
        String customerId = JOptionPane.showInputDialog(this, "삭제할 상품의 고유번호를 입력하세요:");
        if (customerId == null || customerId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "상품 고유번호를 입력하세요.");
            return;
        }

        Customer customer = customerDAO.getCustomerById(customerId);
        if (customer == null) {
            JOptionPane.showMessageDialog(this, "존재하지 않는 상품입니다.");
            return;
        }

        customerDAO.deleteCustomer(customerId);
        loadCustomers();
    }

    private void searchCustomer() {
        String customerId = JOptionPane.showInputDialog(this, "검색할 상품의 고유번호를 입력하세요:");
        if (customerId == null || customerId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "상품 고유번호를 입력하세요.");
            return;
        }
        Customer customer = customerDAO.getCustomerById(customerId);
        if (customer != null) {
            tableModel.setRowCount(0); // Clear existing data
            tableModel.addRow(new Object[]{
            	customer.getCustomerId(),
            	customer.getName(),
            	customer.getPhone(),
            	customer.getEmail(),
            	customer.getAddress(),
            	customer.getRDate(),
            	customer.getCustomerCpw()
            });
        } else {
            JOptionPane.showMessageDialog(this, "존재하지 않는 상품입니다.");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CustomerPanel().setVisible(true);
            }
        });
    }
}
