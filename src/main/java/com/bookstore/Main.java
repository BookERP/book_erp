package main.java.com.bookstore;

import main.java.com.bookstore.controller.AuthController;
import main.java.com.bookstore.service.AuthService;
import main.java.com.bookstore.view.LoginPanel;
import main.java.com.bookstore.view.RegisterPanel;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private LoginPanel loginPanel;
    private RegisterPanel registerPanel;
    private AuthController authController;

    public Main() {
        setTitle("Bookstore Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        initControllers();
    }

    private void initComponents() {
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        loginPanel = new LoginPanel();
        registerPanel = new RegisterPanel();

        contentPanel.add(loginPanel, "login");
        contentPanel.add(registerPanel, "register");

        add(contentPanel);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem loginItem = new JMenuItem("Login");
        JMenuItem registerItem = new JMenuItem("Register");

        loginItem.addActionListener(e -> cardLayout.show(contentPanel, "login"));
        registerItem.addActionListener(e -> cardLayout.show(contentPanel, "register"));

        menu.add(loginItem);
        menu.add(registerItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    private void initControllers() {
        AuthService authService = new AuthService();
        authController = new AuthController(authService, loginPanel, registerPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new main.java.com.bookstore.Main().setVisible(true);
        });
    }
}