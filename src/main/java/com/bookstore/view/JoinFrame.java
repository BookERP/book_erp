package main.java.com.bookstore.view;

import main.java.com.bookstore.controller.UserController;
import main.java.com.bookstore.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinFrame extends JFrame {
    private UserController userController;
    private JTextField accountField,usernameField;
    private JPasswordField passwordField,confirmPasswordField;
    private JTextField phoneField;
    private JButton joinButton,cancelButton;

    public JoinFrame() {
        userController = new UserController();

        setTitle("회원가입");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 240, 240));


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));



        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 10));
        // 로고 (가정)
        JLabel logoLabel = new JLabel("회원가입", SwingConstants.CENTER);
        logoLabel.setFont(new Font("맑은 고딕", Font.BOLD, 28));
        logoLabel.setForeground(new Color(50, 50, 50));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(logoLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        //입력
        formPanel.add(new JLabel("계정(한글됨):"));
        accountField = new JTextField();
        formPanel.add(accountField);

        formPanel.add(new JLabel("비번:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        formPanel.add(new JLabel("비밀번호 확인:"));
        confirmPasswordField = new JPasswordField();
        formPanel.add(confirmPasswordField);
        mainPanel.add(formPanel);

        formPanel.add(new JLabel("연락처:"));
        phoneField = new JPasswordField();
        formPanel.add(phoneField);



        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(new Color(240, 240, 240));
        joinButton = new JButton("가입하기");
        cancelButton = new JButton("취소");

        styleButton(joinButton);
        styleButton(cancelButton);
        buttonPanel.add(joinButton);
        buttonPanel.add(cancelButton);

        mainPanel.add(buttonPanel);

        add(mainPanel, BorderLayout.CENTER);

        // 회원가입 정상작동하면

        joinButton.addActionListener(e -> joinUser());
        cancelButton.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });

        setVisible(true);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginFrame();
            }
        });

        setVisible(true);
    }
    // 컴포넌트 배치

    private void addInputField(JPanel panel, String labelText, JTextField field) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        field.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        panel.add(field);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        button.setBackground(new Color(59, 89, 182));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(120, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    // ...
    private void joinUser() {
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

    public static void main(String[] args) {
        new JoinFrame().setVisible(true);
    }
}
