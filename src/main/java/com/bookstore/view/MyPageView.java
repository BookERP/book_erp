package main.java.com.bookstore.view;

import main.java.com.bookstore.controller.UserController;

import javax.swing.*;
import java.awt.*;

public class MyPageView extends JPanel {
    private UserController userController;

    public MyPageView() {
        initComponents();
    }

    private void initComponents() {
        userController = new UserController();

        setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel idLabel = new JLabel("아이디:");
        JLabel idValueLabel = new JLabel("test");
        JLabel nameLabel = new JLabel("이름:");
        JLabel nameValueLabel = new JLabel("신한");
        JLabel phoneLabel = new JLabel("전화번호:");
        JLabel phoneValueLabel = new JLabel("010-1234-5678");
        JLabel purchaseLabel = new JLabel("구매 내역:");
        JLabel purchaseValueLabel = new JLabel("책1, 책2, 책3");
        JLabel rentLabel = new JLabel("대여 내역:");
        JLabel rentValueLabel = new JLabel("책4, 책5");

        panel.add(idLabel);
        panel.add(idValueLabel);
        panel.add(nameLabel);
        panel.add(nameValueLabel);
        panel.add(phoneLabel);
        panel.add(phoneValueLabel);
        panel.add(purchaseLabel);
        panel.add(purchaseValueLabel);
        panel.add(rentLabel);
        panel.add(rentValueLabel);

        add(panel);
        setVisible(true);
    }
}