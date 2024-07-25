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
    private JButton loginButton,joinButton,findButton;
    public LoginFrame() {
        userController = new UserController();

        setTitle("로그인");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        // ...

        // 로그인 폼 패널
        JPanel formPanel = new JPanel(new GridLayout(3, 2));
        formPanel.add(new JLabel("계정:"));
        accountField = new JTextField();
        formPanel.add(accountField);
        formPanel.add(new JLabel("비밀번호:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);
        loginButton = new JButton("로그인");
        formPanel.add(loginButton);
        joinButton = new JButton("회원가입");
        formPanel.add(joinButton);
        findButton = new JButton("이ㅏ름으로 아이디/ㅂ번찾기");
        formPanel.add(findButton);
        setVisible(true);
        // 액션 리스너 등록
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });

        // 컴포넌트 배치
        add(formPanel);

        // ...
    }

    private void loginUser() {
        // 사용자 로그인 로직 구현
        String account = accountField.getText();
        String password = new String(passwordField.getPassword());

        boolean success = userController.login(account, password);
        if (success) {
            JOptionPane.showMessageDialog(this, "로그인 성공!");
            dispose(); // 로그인 성공 후 창 닫기
            // 메인 화면으로 이동하는 로직 추가
        } else {
            JOptionPane.showMessageDialog(this, "로그인 실패. 계정과 비밀번호를 확인해주세요.");
        }
    }

    // ...
}
