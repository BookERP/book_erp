package main.java.com.bookstore.view;

import main.java.com.bookstore.controller.CustomerController;
import main.java.com.bookstore.model.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CustomerView extends JFrame {
    private CustomerController customerController;
    private JTable customerTable;
    private JTextField searchField;
    private JButton searchButton;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    public CustomerView() {
        customerController = new CustomerController();

        setTitle("고객 관리");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        //고객 검색 패널
        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        searchButton = new JButton("검색");
        searchPanel.add(new JLabel("고객 검색:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // 고객 목록 테이블
        String[] columnNames = {"고객 ID", "이름", "전화번호", "대여 정보", "구매 정보"};
        Object[][] data = {
                {"1", "홍길동", "010-1234-5678"},
                {"2", "김철수", "010-2345-6789"},
                {"3", "이영희", "010-3456-7890"}
        };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        customerTable = new JTable(model);
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);


        // 버튼 패널
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("고객 추가");
        updateButton = new JButton("고객 수정");
        deleteButton = new JButton("고객 삭제");
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        JButton purchaseButton = new JButton("책 구매하기");
        JButton rentButton = new JButton("책 대여하기");
        buttonPanel.add(purchaseButton);
        buttonPanel.add(rentButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
        searchPanel = new JPanel();
        searchField = new JTextField(20);
        searchButton = new JButton("검색");
        searchPanel.add(new JLabel("고객 검색:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = searchField.getText();
                searchCustomers(keyword);
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddCustomerDialog();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUpdateCustomerDialog();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedCustomer();
            }
        });

        // 컴포넌트 배치
        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // ...
    }

    private void searchCustomers(String keyword) {
        // 고객 검색 로직 구현
        List<Customer> customers = customerController.searchCustomers(keyword);
        DefaultTableModel model = (DefaultTableModel) customerTable.getModel();
        model.setRowCount(0); // 기존 데이터 초기화
        for (Customer customer : customers) {
            Object[] row = {
                    customer.getCustomerId(),
                    customer.getName(),
                    customer.getPhone(),
//                    getCustomerRentalInfo(customer.getCustomerId()),
//                    getCustomerPurchaseInfo(customer.getCustomerId())
            };
            model.addRow(row);
        }
    }


    private void showAddCustomerDialog() {
        // 고객 추가 다이얼로그 표시
        // ...
    }

    private void showUpdateCustomerDialog() {
        // 고객 수정 다이얼로그 표시
        // ...
    }

    private void deleteSelectedCustomer() {
        // 선택된 고객 삭제 로직 구현
        int selectedRow = customerTable.getSelectedRow();
        if (selectedRow != -1) {
            String customerId = (String) customerTable.getValueAt(selectedRow, 0);
            customerController.deleteCustomer(customerId);
            ((DefaultTableModel) customerTable.getModel()).removeRow(selectedRow);
        }
    }

    // ...
}
