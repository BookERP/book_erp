package main.java.com.bookstore.view;

import main.java.com.bookstore.controller.BookController;
import main.java.com.bookstore.controller.CustomerController;
import main.java.com.bookstore.controller.UserController;

import javax.swing.*;

public class MainFrame extends JFrame {
    private BookController bookController;
    private CustomerController customerController;
    private UserController userController;

    public MainFrame(String account) {
        bookController = new BookController();
        customerController = new CustomerController();
        userController = new UserController();

        setTitle("서점ERP프로그램");
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
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel bookPanel = new JPanel();
        JPanel customerPanel = new JPanel();
        JPanel myPagePanel = new JPanel();
        tabbedPane.addTab("도서 관리", bookPanel);
        tabbedPane.addTab("고객 관리", customerPanel);
        tabbedPane.addTab("내 정보", myPagePanel);
        add(tabbedPane);

        setVisible(true);
    }

    public static void main(String[] args) {
//        LoginFrame login = new LoginFrame();
//		login.setVisible(true);
        new MainFrame("account");

    }



}