package main.java.com.bookstore.view;

import main.java.com.bookstore.controller.UserController;
import main.java.com.bookstore.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinFrame extends JFrame {
    private UserController userController;
    private JTextField accountField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField phoneField;
    private JButton joinButton;

    public JoinFrame() {
        userController = new UserController();

        setTitle("회원가입");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 250);
        setLocationRelativeTo(null);

        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        formPanel.add(new JLabel("계정:"));
        accountField = new JTextField();
        formPanel.add(accountField);
        formPanel.add(new JLabel("비밀번호:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);
        formPanel.add(new JLabel("비밀번호 확인:"));
        confirmPasswordField = new JPasswordField();
        formPanel.add(confirmPasswordField);
        formPanel.add(new JLabel("전화번호:"));
        phoneField = new JTextField();
        formPanel.add(phoneField);
        joinButton = new JButton("회원가입");
        formPanel.add(joinButton);

        // 액션 리스너 등록
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                joinUser();
            }
        });

        // 컴포넌트 배치
        add(formPanel);

        // ...
    }

    private void joinUser() {
        // 사용자 등록 로직 구현
        String account = accountField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String phone = phoneField.getText();

        if (password.equals(confirmPassword)) {
            User user = new User();
            user.setAccount(account);
            user.setPw(password);
            user.setPhone(phone);

            userController.registerUser(user);
            JOptionPane.showMessageDialog(this, "회원가입이 완료되었습니다.");
            clearFields();
            dispose(); // 회원가입 완료 후 창 닫기
        } else {
            JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.");
        }
    }

    private void clearFields() {
        accountField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
        phoneField.setText("");
    }

    // ...
}
