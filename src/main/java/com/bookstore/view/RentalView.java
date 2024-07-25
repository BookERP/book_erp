package main.java.com.bookstore.view;


import main.java.com.bookstore.controller.RentalController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RentalView extends JFrame {
    private RentalController rentalController;
    private JComboBox<String> bookComboBox;
    private JComboBox<String> customerComboBox;
    private JTextField rentalDateField;
    private JTextField returnDateField;
    private JButton rentalButton;

    public RentalView() {
        rentalController = new RentalController();
        initComponents();
    }

    private void initComponents() {
        setTitle("도서 대여");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 200));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        panel.add(new JLabel("도서:"));
        bookComboBox = new JComboBox<>(rentalController.getBookTitles());
        panel.add(bookComboBox);

        panel.add(new JLabel("고객:"));
        customerComboBox = new JComboBox<>(rentalController.getCustomerNames());
        panel.add(customerComboBox);

        panel.add(new JLabel("대여일:"));
        rentalDateField = new JTextField();
        panel.add(rentalDateField);

        panel.add(new JLabel("반납일:"));
        returnDateField = new JTextField();
        panel.add(returnDateField);

        rentalButton = new JButton("대여");
        rentalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rentalBook();
            }
        });
        panel.add(new JLabel());
        panel.add(rentalButton);

        add(panel);
        pack();
        setLocationRelativeTo(null);
    }

    private void rentalBook() {
        String bookTitle = (String) bookComboBox.getSelectedItem();
        String customerName = (String) customerComboBox.getSelectedItem();
        String rentalDate = rentalDateField.getText();
        String returnDate = returnDateField.getText();

        rentalController.rentalBook(bookTitle, customerName, rentalDate, returnDate);

        JOptionPane.showMessageDialog(this, "도서가 대여되었습니다.");
        dispose();
    }
}