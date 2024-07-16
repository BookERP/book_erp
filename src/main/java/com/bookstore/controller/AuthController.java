package main.java.com.bookstore.controller;

import main.java.com.bookstore.model.User;
import main.java.com.bookstore.service.AuthService;
import main.java.com.bookstore.view.LoginPanel;
import main.java.com.bookstore.view.RegisterPanel;

import javax.swing.*;
//import jakarta.swing.*;
public class AuthController {
    private AuthService authService;
    private LoginPanel loginPanel;
    private RegisterPanel registerPanel;

    public AuthController(AuthService authService, LoginPanel loginPanel, RegisterPanel registerPanel) {
        this.authService = authService;
        this.loginPanel = loginPanel;
        this.registerPanel = registerPanel;
        initListeners();
    }

    private void initListeners() {
        loginPanel.setLoginButtonListener(e -> login());
        registerPanel.setRegisterButtonListener(e -> register());
    }

    private void login() {
        String username = loginPanel.getUsername();
        String password = loginPanel.getPassword();
        User user = authService.login(username, password);
        if (user != null) {
            JOptionPane.showMessageDialog(null, "로그인 성공!");
            // TODO: 메인 화면으로 전환하는 로직
        } else {
            JOptionPane.showMessageDialog(null, "로그인 실패. 사용자명과 비밀번호를 확인해주세요.");
        }
    }


    private void register() {
        String username = registerPanel.getUsername();
        String password = registerPanel.getPassword();
        String email = registerPanel.getEmail();

        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "모든 필드를 입력해주세요.");
            return;
        }

        boolean success = authService.register(username, password, email);
        if (success) {
            JOptionPane.showMessageDialog(null, "회원가입 성공!");
            // TODO: 로그인 화면으로 전환하는 로직
        } else {
            JOptionPane.showMessageDialog(null, "회원가입 실패. 다시 시도해주세요.");
        }
    }
}
