package main.java.com.bookstore.view;

import main.java.com.bookstore.controller.BookController;
import main.java.com.bookstore.controller.CustomerController;
import main.java.com.bookstore.controller.UserController;

import javax.swing.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JTabbedPane tabbedPane;
    private JPanel bookPanel;
    private JPanel customerPanel;
    private JPanel myPagePanel;

    public MainFrame() {
        initComponents();
    }

    private void initComponents() {
        setTitle("서점 ERP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // 메뉴바 생성
        JMenuBar menuBar = new JMenuBar();
        JMenu adminMenu = new JMenu("관리자 전환");
        JMenu helpMenu = new JMenu("도움말");
        menuBar.add(adminMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);

        // 탭 패널 생성
        tabbedPane = new JTabbedPane();

        // 도서 관리 패널 생성
        bookPanel = createBookPanel();
        tabbedPane.addTab("도서 관리", bookPanel);

        // 고객 관리 패널 생성
        customerPanel = createCustomerPanel();
        tabbedPane.addTab("고객 관리", customerPanel);

        // 내 정보 패널 생성
        myPagePanel = createMyPagePanel();
        tabbedPane.addTab("내 정보", myPagePanel);

        // 탭 패널을 메인 프레임에 추가
        add(tabbedPane);
    }

    private JPanel createBookPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // 도서 검색 패널
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("도서 검색:"));
        JTextField searchField = new JTextField(20);
        searchPanel.add(searchField);
        JButton searchButton = new JButton("검색");
        searchPanel.add(searchButton);
        panel.add(searchPanel, BorderLayout.NORTH);

        // 도서 목록 테이블
        String[] columnNames = {"도서 ID", "ISBN", "제목", "저자", "출판사", "출판일", "가격", "카테고리", "재고"};
        Object[][] rowData = {{"1", "1234567890", "test1", "test", "test", "2022-01-01", "23132", "test", "1"},
                {"2", "9876543210", "test", "test2", "test", "2023-12-15", "35000", "test", "1"}};
        JTable bookTable = new JTable(rowData, columnNames);
        JScrollPane scrollPane = new JScrollPane(bookTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // 도서 추가, 수정, 삭제 버튼 패널
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("추가");
        JButton updateButton = new JButton("수정");
        JButton deleteButton = new JButton("삭제");
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createCustomerPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // 고객 검색 패널
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("고객 검색:"));
        JTextField searchField = new JTextField(20);
        searchPanel.add(searchField);
        JButton searchButton = new JButton("검색");
        searchPanel.add(searchButton);
        panel.add(searchPanel, BorderLayout.NORTH);

        // 고객 목록 테이블
        String[] columnNames = {"고객 ID", "이름", "전화번호", "대여 정보", "구매 정보"};
        Object[][] rowData = {{"1", "홍엽", "010-1234-5678", "able", "book"},
                {"2", "test", "010-9876-5432", "", "book"}};
        JTable customerTable = new JTable(rowData, columnNames);
        JScrollPane scrollPane = new JScrollPane(customerTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // 고객 추가, 수정, 삭제 버튼 패널
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("추가");
        JButton updateButton = new JButton("수정");
        JButton deleteButton = new JButton("삭제");
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createMyPagePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        panel.add(new JLabel("권한:"));
        panel.add(new JLabel("Admin"));

        panel.add(new JLabel("이름:"));
        panel.add(new JLabel("홍엽"));

        panel.add(new JLabel("연락처:"));
        panel.add(new JLabel("010-1234-5678"));

        return panel;


    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            }
        });
    }
}

