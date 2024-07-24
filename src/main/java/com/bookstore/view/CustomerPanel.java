package main.java.com.bookstore.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import main.java.com.bookstore.DAO.CustomerDAO;
import main.java.com.bookstore.model.Customer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

public class CustomerPanel extends JFrame {
    private CustomerDAO customerDAO;
    private DefaultTableModel tableModel;
    private JTable customerTable;

    public CustomerPanel() {
        customerDAO = new CustomerDAO();
        setTitle("고객 관리");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // 버튼 패널
        JPanel buttonPanel = new JPanel();
        JButton btnLoad = new JButton("전체보기");
        JButton btnSearch = new JButton("검색");
        JButton btnUpdate = new JButton("수정");
        JButton btnDelete = new JButton("삭제");

        buttonPanel.add(btnLoad);
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        // 고객 정보를 표시할 테이블
        tableModel = new DefaultTableModel(new String[]{"고객 ID", "이름", "전화번호", "이메일", "주소", "가입일자", "비밀번호"}, 0);
        customerTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(customerTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // 버튼에 대한 이벤트 리스너 등록
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadCustomers();
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchCustomer();
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

        add(mainPanel);
    }
    
    // 전체 고객 정보 로드 및 테이블에 표시하는 메소드
    private void loadCustomers() {
        List<Customer> customers = customerDAO.getAllCustomers();
        tableModel.setRowCount(0); // Clear existing data
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (Customer customer : customers) {
            Object[] rowData = new Object[7]; // 7개의 열 데이터 배열

            rowData[0] = customer.getCustomerId();
            rowData[1] = customer.getCName();
            rowData[2] = customer.getCPhone();
            rowData[3] = customer.getCEmail();
            rowData[4] = customer.getCAddress();
            rowData[5] = (customer.getRDate() != null) ? dateFormat.format(customer.getRDate()) : ""; // 날짜가 null이면 빈 문자열 처리
            rowData[6] = customer.getCpw(); // 비밀번호 열 추가

            tableModel.addRow(rowData);
        }
    }

    // 고객 검색 기능 구현 (아이디로 검색)
    private void searchCustomer() {
        String customerId = JOptionPane.showInputDialog(this, "검색할 고객의 ID를 입력하세요:");
        if (customerId != null && !customerId.isEmpty()) {
            Customer customer = customerDAO.getCustomerByID(customerId);
            if (customer != null) {
                tableModel.setRowCount(0); // Clear existing data
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = (customer.getRDate() != null) ? dateFormat.format(customer.getRDate()) : ""; // 날짜가 null이면 빈 문자열 처리
                tableModel.addRow(new Object[]{
                    customer.getCustomerId(),
                    customer.getCName(),
                    customer.getCPhone(),
                    customer.getCEmail(),
                    customer.getCAddress(),
                    formattedDate,
                    customer.getCpw() // 비밀번호 열 추가
                });
            } else {
                JOptionPane.showMessageDialog(this, "해당 고객을 찾을 수 없습니다.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "고객 ID를 입력하세요.");
        }
    }

    // 고객 수정 기능 구현
    private void updateCustomer() {
        String customerId = JOptionPane.showInputDialog(this, "수정할 고객의 ID를 입력하세요:");
        if (customerId != null && !customerId.isEmpty()) {
            Customer customer = customerDAO.getCustomerByID(customerId);
            if (customer != null) {
                // 고객 정보 입력 받기
                JTextField txtName = new JTextField(customer.getCName());
                JTextField txtPhone = new JTextField(customer.getCPhone());
                JTextField txtEmail = new JTextField(customer.getCEmail());
                JTextField txtAddress = new JTextField(customer.getCAddress());
                JTextField txtPassword = new JTextField(customer.getCpw());

                JPanel panel = new JPanel(new GridLayout(0, 1));
                panel.add(new JLabel("이름:"));
                panel.add(txtName);
                panel.add(new JLabel("전화번호:"));
                panel.add(txtPhone);
                panel.add(new JLabel("이메일:"));
                panel.add(txtEmail);
                panel.add(new JLabel("주소:"));
                panel.add(txtAddress);
                panel.add(new JLabel("비밀번호:"));
                panel.add(txtPassword);

                int result = JOptionPane.showConfirmDialog(null, panel, "고객 정보 수정",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    // 사용자가 OK를 클릭했을 때, 고객 정보 업데이트
                    customer.setCName(txtName.getText());
                    customer.setCPhone(txtPhone.getText());
                    customer.setCEmail(txtEmail.getText());
                    customer.setCAddress(txtAddress.getText());
                    customer.setCpw(txtPassword.getText());

                    customerDAO.updateCustomer(customer);
                    loadCustomers(); // 업데이트 후 고객 정보 다시 로드
                }
            } else {
                JOptionPane.showMessageDialog(this, "해당 고객을 찾을 수 없습니다.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "고객 ID를 입력하세요.");
        }
    }

    // 고객 삭제 기능 구현
    private void deleteCustomer() {
        String customerId = JOptionPane.showInputDialog(this, "삭제할 고객의 ID를 입력하세요:");
        if (customerId != null && !customerId.isEmpty()) {
            Customer customer = customerDAO.getCustomerByID(customerId);
            if (customer != null) {
                int option = JOptionPane.showConfirmDialog(this, "정말로 고객 정보를 삭제하시겠습니까?", "삭제 확인", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    customerDAO.deleteCustomer(customerId);
                    loadCustomers(); // 삭제 후 고객 정보 다시 로드
                    JOptionPane.showMessageDialog(this, "삭제가 완료되었습니다.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "해당 고객을 찾을 수 없습니다.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "고객 ID를 입력하세요.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CustomerPanel customerPanel = new CustomerPanel();
                customerPanel.setVisible(true);
            }
        });
    }
}
