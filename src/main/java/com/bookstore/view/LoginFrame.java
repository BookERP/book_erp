package main.java.com.bookstore.view;

import main.java.com.bookstore.controller.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private UserController userController;
    private JTextField accountField;
    private JPasswordField passwordField;
    private JButton loginButton, joinButton, findButton;

    public LoginFrame() {
        userController = new UserController();

        setTitle("도서관 로그인");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 메인 패널
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 로고 라벨
        JLabel logoLabel = new JLabel("도서관 로그인", SwingConstants.CENTER);
        logoLabel.setFont(new Font("맑은 고딕", Font.BOLD, 24));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(logoLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // 입력 필드 패널
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 10));
        inputPanel.add(new JLabel("계정:"));
        accountField = new JTextField();
        inputPanel.add(accountField);
        inputPanel.add(new JLabel("비밀번호:"));
        passwordField = new JPasswordField();
        inputPanel.add(passwordField);
        mainPanel.add(inputPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // 버튼 패널
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loginButton = new JButton("로그인");
        joinButton = new JButton("회원가입");
        findButton = new JButton("아이디/비번 찾기");

        styleButton(loginButton);
        styleButton(joinButton);
        styleButton(findButton);

        buttonPanel.add(loginButton);
        buttonPanel.add(joinButton);
        buttonPanel.add(findButton);

        mainPanel.add(buttonPanel);

        add(mainPanel, BorderLayout.CENTER);

        // 로그인 버튼
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                loginUser();
                dispose();
                new MainFrame();
            }
        });
        setVisible(true);
        // 회원가입 버튼
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JoinFrame();
            }
        });
        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        button.setBackground(new Color(59, 89, 182));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(120, 30));
    }

    //로그인페이지 로 이동하기.
    private void loginUser() {
        String account = accountField.getText();
        String password = new String(passwordField.getPassword());
        boolean success = userController.login(account, password);
        if (success) {
            JOptionPane.showMessageDialog(this, "로그인 성공");
            dispose(); // 로그인 성공 후 창 닫고
            //메인프레임생성과 함께 account 전달
            SwingUtilities.invokeLater(MainFrame::new);
        } else {
            JOptionPane.showMessageDialog(this, "로그인 실패.");
        }

        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // 현재 로그인 창 닫기
                new JoinFrame(); // 회원가입 창 열기
            }
        });
    }

}