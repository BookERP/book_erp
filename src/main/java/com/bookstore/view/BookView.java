package main.java.com.bookstore.view;

import main.java.com.bookstore.controller.BookController;
import main.java.com.bookstore.model.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BookView extends JFrame {
    private BookController bookController;
    private JTable bookTable;
    private JTextField searchField;
    private JButton searchButton;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;

    public BookView() {
        bookController = new BookController();

        setTitle("도서 관리");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        //도서 검색
        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        searchButton = new JButton("검색");
        searchPanel.add(new JLabel("도서 검색:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // 도서 목록 테이블
        String[] columnNames = {"도서 ID", "ISBN", "제목", "저자", "출판사", "출판일", "가격", "카테고리", "재고"};
        Object[][] data = {
                {"1", "1234567890", "책1", "저자1", "출판사1", "2021-01-01", "10000", "카테고리1", "5"},
                {"2", "2345678901", "책2", "저자2", "출판사2", "2021-02-01", "15000", "카테고리2", "3"},
                {"3", "3456789012", "책3", "저자3", "출판사3", "2021-03-01", "20000", "카테고리3", "2"}
        };
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // 버튼 패널
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("도서 추가");
        JButton editButton = new JButton("도서 수정");
        JButton deleteButton = new JButton("도서 삭제");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        //패널 배치
        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(panel);
        // 액션 리스너 등록
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = searchField.getText();
                searchBooks(keyword);
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddBookDialog();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUpdateBookDialog();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedBook();
            }
        });
        setVisible(true);
    }

    private void searchBooks(String keyword) {
        // 도서 검색 로직 구현
        List<Book> books = bookController.searchBooks(keyword);
        DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
        model.setRowCount(0); // 기존 데이터 초기화
        for (Book book : books) {
            Object[] row = {book.getBookId(), book.getIsbn(), book.getAuthor(),
                    book.getAuthor(), book.getPublisher(), book.getPublishDate(),
                    book.getPrice(), book.getCategory(), book.getCurrent()};
            model.addRow(row);
        }
    }

    private void showAddBookDialog() {
        // 도서 추가 다이얼로그 표시
        // ...
    }

    private void showUpdateBookDialog() {
        // 도서 수정 다이얼로그 표시
        // ...
    }
    // 컴포넌트 배치


    // ...
    private void deleteSelectedBook() {
        // 선택된 도서 삭제 로직 구현
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow != -1) {
            String bookId = (String) bookTable.getValueAt(selectedRow, 0);
            bookController.deleteBook(bookId);
            ((DefaultTableModel) bookTable.getModel()).removeRow(selectedRow);
        }
    }
    // ...
}

