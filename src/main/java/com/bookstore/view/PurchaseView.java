package main.java.com.bookstore.view;

import main.java.com.bookstore.controller.PurchaseController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PurchaseView extends JFrame {
    private PurchaseController purchaseController;
    private JComboBox<String> bookComboBox;
    private JComboBox<String> customerComboBox;
    private JTextField purchaseDateField;
    private JButton purchaseButton;

    public PurchaseView() {
        purchaseController = new PurchaseController();
        initComponents();
    }

    private void initComponents() {
        setTitle("도서 구매");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 150));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        panel.add(new JLabel("도서:"));
        bookComboBox = new JComboBox<>(purchaseController.getBookTitles());
        panel.add(bookComboBox);

        panel.add(new JLabel("고객:"));
        customerComboBox = new JComboBox<>(purchaseController.getCustomerNames());
        panel.add(customerComboBox);

        panel.add(new JLabel("구매일:"));
        purchaseDateField = new JTextField();
        panel.add(purchaseDateField);

        purchaseButton = new JButton("구매");
        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                purchaseBook();
            }
        });
        panel.add(new JLabel());
        panel.add(purchaseButton);

        add(panel);
        pack();
        setLocationRelativeTo(null);
    }

    private void purchaseBook() {
        String bookTitle = (String) bookComboBox.getSelectedItem();
        String customerName = (String) customerComboBox.getSelectedItem();
        String purchaseDate = purchaseDateField.getText();

        purchaseController.purchaseBook(bookTitle, customerName, purchaseDate);

        JOptionPane.showMessageDialog(this, "도서가 구매되었습니다.");
        dispose();
    }
}
