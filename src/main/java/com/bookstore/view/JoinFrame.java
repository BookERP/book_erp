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
    private JPanel temp;
    public JoinFrame() {
        userController = new UserController();

        setTitle("회원가입");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 240, 240));

        //회원가입 패널
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 10));

        // 회원가입 라벨(로고)
        JLabel logoLabel = new JLabel("회원가입", SwingConstants.CENTER);
        logoLabel.setFont(new Font("맑은 고딕", Font.BOLD, 28));
        logoLabel.setForeground(new Color(50, 50, 50));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(logoLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // 입력 텍스트필드
        formPanel.add(new JLabel("계정(한글됨):"));
        accountField = new JTextField();
        formPanel.add(accountField);

        formPanel.add(new JLabel("비번:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        formPanel.add(new JLabel("비밀번호 확인:"));
        confirmPasswordField = new JPasswordField();
        formPanel.add(confirmPasswordField);

        formPanel.add(new JLabel("연락처:"));
        phoneField = new JTextField();
        formPanel.add(phoneField);

        // 입력 버튼
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(new Color(240, 240, 240));
        joinButton = new JButton("가입하기");
        cancelButton = new JButton("취소");

        //스타일 적용
        styleButton(joinButton);
        styleButton(cancelButton);
        buttonPanel.add(joinButton);
        buttonPanel.add(cancelButton);


        //메인 프레임 생성
        add(mainPanel, BorderLayout.CENTER);
        //텍스트필드(계정,비번,비번컴펌,연락처) 메인프레임 패널에 생성.
        mainPanel.add(formPanel);
        //버튼(가입,취소) 메인프레임 패널에 생성.
        mainPanel.add(buttonPanel);

        // 회원가입 submit.로그인 페이지로 이동.
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                joinUser();
                dispose();
                new LoginFrame();
            }
        });
        // 취소 버튼
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
    private void styleButton(JButton button) {
        button.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        button.setBackground(new Color(59, 89, 182));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(120, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    //    password.equals(confirmPassword) 이거 제대로 작동 안함
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

            //registerUser로 넘김
            userController.registerUser(user);
            JOptionPane.showMessageDialog(this, "회원가입 성공");
            dispose(); // 회원가입 완료 후 창 닫기
        } else {
            clearFields();
            JOptionPane.showMessageDialog(this, "비밀번호 불일치");
        }
    }
    private void clearFields() {
        accountField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
        phoneField.setText("");
    }

}
