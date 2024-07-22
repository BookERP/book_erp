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
            // 메인 화면으로 전환하는 로직
        } else {
            JOptionPane.showMessageDialog(null, "로그인 실패. 사용자명과 비밀번호를 확인해주세요.");
        }
    }

    private void register() {
        // 회원가입 로직 구현
    }
}

