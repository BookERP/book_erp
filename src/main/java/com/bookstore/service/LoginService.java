package main.java.com.bookstore.service;

import javax.swing.*;
import main.java.com.bookstore.util.ConnectionHelper;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LoginService extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPanel loginPanel;
    private JPanel registrationPanel;

    // Instance variables for login fields
    private JTextField txtUsername;
    private JPasswordField txtPassword;

    // Instance variables for registration fields
    private JTextField txtId, txtName, txtPhone, txtEmail, txtAddress;
    private JPasswordField txtRegPassword;

    public LoginService() {
        setTitle("User Management");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Login Panel
        loginPanel = createLoginPanel();
        mainPanel.add(loginPanel, "login");

        // Registration Panel
        registrationPanel = createRegistrationPanel();
        mainPanel.add(registrationPanel, "register");

        add(mainPanel);

        // Show login panel initially
        cardLayout.show(mainPanel, "login");
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(null);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(20, 30, 80, 25);
        panel.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(100, 30, 160, 25);
        panel.add(txtUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(20, 70, 80, 25);
        panel.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(100, 70, 160, 25);
        panel.add(txtPassword);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(100, 110, 100, 25);
        panel.add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser(txtUsername.getText(), new String(txtPassword.getPassword()));
            }
        });

        JButton btnGoToRegister = new JButton("Register");
        btnGoToRegister.setBounds(100, 150, 100, 25);
        panel.add(btnGoToRegister);

        btnGoToRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "register");
            }
        });

        return panel;
    }

    private JPanel createRegistrationPanel() {
        JPanel panel = new JPanel(null);

        JLabel lblUId = new JLabel("아이디:");
        lblUId.setBounds(20, 30, 80, 25);
        panel.add(lblUId);

        txtId = new JTextField();
        txtId.setBounds(100, 30, 160, 25);
        panel.add(txtId);

        JLabel lblPassword = new JLabel("비밀번호:");
        lblPassword.setBounds(20, 70, 80, 25);
        panel.add(lblPassword);

        txtRegPassword = new JPasswordField();
        txtRegPassword.setBounds(100, 70, 160, 25);
        panel.add(txtRegPassword);

        JLabel lblName = new JLabel("이름:");
        lblName.setBounds(20, 110, 80, 25);
        panel.add(lblName);

        txtName = new JTextField();
        txtName.setBounds(100, 110, 160, 25);
        panel.add(txtName);

        JLabel lblPhone = new JLabel("전화번호:");
        lblPhone.setBounds(20, 150, 80, 25);
        panel.add(lblPhone);

        txtPhone = new JTextField();
        txtPhone.setBounds(100, 150, 160, 25);
        panel.add(txtPhone);

        JLabel lblEmail = new JLabel("이메일:");
        lblEmail.setBounds(20, 190, 80, 25);
        panel.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(100, 190, 160, 25);
        panel.add(txtEmail);

        JLabel lblAddress = new JLabel("주소:");
        lblAddress.setBounds(20, 230, 80, 25);
        panel.add(lblAddress);

        txtAddress = new JTextField();
        txtAddress.setBounds(100, 230, 160, 25);
        panel.add(txtAddress);

        JButton btnRegister = new JButton("Register");
        btnRegister.setBounds(100, 270, 100, 25);
        panel.add(btnRegister);

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser(txtId.getText(), new String(txtRegPassword.getPassword()), txtName.getText(), txtPhone.getText(), txtEmail.getText(), txtAddress.getText());
            }
        });

        JButton btnGoToLogin = new JButton("Back to Login");
        btnGoToLogin.setBounds(100, 310, 150, 25);
        panel.add(btnGoToLogin);

        btnGoToLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "login");
            }
        });

        return panel;
    }

    private void loginUser(String username, String password) {
        try {
            Connection conn = ConnectionHelper.getConnection("oracle");
            String query = "SELECT * FROM CUSTOMER WHERE CUSTOMERID = ? AND CUSTOMERCPW = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                showDashboard();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void registerUser(String username, String password, String name, String phone, String email, String address) {
        Timestamp registrationDate = Timestamp.valueOf(LocalDateTime.now());

        try {
            Connection conn = ConnectionHelper.getConnection("oracle");
            String query = "INSERT INTO Customer (CUSTOMERID, CUSTOMERCPW, NAME, PHONE, EMAIL, ADDRESS, RDATE) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, name);
            pst.setString(4, phone);
            pst.setString(5, email);
            pst.setString(6, address);
            pst.setTimestamp(7, registrationDate);

            int result = pst.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "회원가입을 축하합니다!!");
                cardLayout.show(mainPanel, "login");
            } else {
                JOptionPane.showMessageDialog(this, "회원가입을 실패하셨습니다. 다시 확인해 주세요.");
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void showDashboard() {
        JFrame dashboard = new JFrame("Dashboard");
        dashboard.setSize(400, 300);
        dashboard.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dashboard.setLocationRelativeTo(null);

        JLabel welcomeLabel = new JLabel("Welcome to the Dashboard!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dashboard.add(welcomeLabel);

        dashboard.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginService().setVisible(true);
            }
        });
    }
}