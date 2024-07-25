package main.java.com.bookstore;

import main.java.com.bookstore.view.LoginFrame;

import javax.swing.*;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new LoginFrame();
            }
        });
    }
}