package main.java.com.bookstore.view;

import main.java.com.bookstore.dao.ProductDAO;
import main.java.com.bookstore.dao.SupplierDAO;
import main.java.com.bookstore.model.Product;
import main.java.com.bookstore.model.Supplier;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductManagementGUI extends JFrame {
    private JTextField txtSearchProductId, txtName, txtAuthor, txtPublisher, txtPrice, txtStockQuantity, txtCategory;
    private JComboBox<String> comboSupplierName;
    private JTable productTable;
    private DefaultTableModel tableModel;
    private ProductDAO productDAO;
    private SupplierDAO supplierDAO;

    public ProductManagementGUI() {
        productDAO = new ProductDAO();
        supplierDAO = new SupplierDAO();
        setTitle("상품 관리");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(8, 2)); // Removed txtProductId from layout
        inputPanel.add(new JLabel("공급체 이름:"));
        comboSupplierName = new JComboBox<>();
        inputPanel.add(comboSupplierName);
        supplierDAO.loadSuppliers(comboSupplierName);

        inputPanel.add(new JLabel("도서명:"));
        txtName = new JTextField();
        inputPanel.add(txtName);

        inputPanel.add(new JLabel("저자:"));
        txtAuthor = new JTextField();
        inputPanel.add(txtAuthor);

        inputPanel.add(new JLabel("출판사:"));
        txtPublisher = new JTextField();
        inputPanel.add(txtPublisher);

        inputPanel.add(new JLabel("가격:"));
        txtPrice = new JTextField();
        inputPanel.add(txtPrice);

        inputPanel.add(new JLabel("재고 수:"));
        txtStockQuantity = new JTextField();
        inputPanel.add(txtStockQuantity);

        inputPanel.add(new JLabel("카테고리:"));
        txtCategory = new JTextField();
        inputPanel.add(txtCategory);

        add(inputPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"상품 고유번호", "공급체 고유번호", "공급체 이름", "도서명", "저자", "출판사", "가격", "재고 수", "카테고리"}, 0);
        productTable = new JTable(tableModel);
        add(new JScrollPane(productTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("추가");
        JButton btnUpdate = new JButton("수정");
        JButton btnDelete = new JButton("삭제");
        JButton btnSearch = new JButton("검색");
        JButton btnSelectAll = new JButton("전체 목록 보기");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnSelectAll);

        add(buttonPanel, BorderLayout.SOUTH);

        btnSelectAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadProducts();
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateProduct();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteProduct();
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchProduct();
            }
        });

        loadProducts();
    }

    private void loadSuppliers() {
        supplierDAO.loadSuppliers(comboSupplierName);
    }

    private void loadProducts() {
        List<Product> products = productDAO.getAllProducts();
        tableModel.setRowCount(0); // Clear existing data
        for (Product product : products) {
            Supplier supplier = supplierDAO.getSupplierById(product.getSID());
            String supplierName = (supplier != null) ? supplier.getName() : "Unknown";
            tableModel.addRow(new Object[]{
                product.getProductId(),
                product.getSID(),
                supplierName,
                product.getPname(),
                product.getAuthor(),
                product.getPublisher(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getCategory()
            });
        }
    }

    private void addProduct() {
        String name = txtName.getText();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "도서명을 입력하세요.");
            return;
        }
        String author = txtAuthor.getText();
        if (author.isEmpty()) {
            JOptionPane.showMessageDialog(this, "저자를 입력하세요.");
            return;
        }
        String publisher = txtPublisher.getText();
        if (publisher.isEmpty()) {
            JOptionPane.showMessageDialog(this, "출판사를 입력하세요.");
            return;
        }
        String price = txtPrice.getText();
        if (price.isEmpty()) {
            JOptionPane.showMessageDialog(this, "가격을 입력하세요.");
            return;
        }
        String stockQuantity = txtStockQuantity.getText();
        if (stockQuantity.isEmpty()) {
            JOptionPane.showMessageDialog(this, "재고 수를 입력하세요.");
            return;
        }
        String category = txtCategory.getText();
        if (category.isEmpty()) {
            JOptionPane.showMessageDialog(this, "카테고리를 입력하세요.");
            return;
        }

        Supplier supplier = supplierDAO.getSupplierByName((String) comboSupplierName.getSelectedItem());
        if (supplier == null) {
            JOptionPane.showMessageDialog(this, "존재하지 않는 공급체입니다.");
            return;
        }

        Product product = new Product();
        product.setProductId(productDAO.getNextProductId()); // Automatically generate product ID
        product.setSID(supplier.getSupplierId());
        product.setPname(txtName.getText());
        product.setAuthor(txtAuthor.getText());
        product.setPublisher(txtPublisher.getText());
        product.setPrice(Double.parseDouble(price));
        product.setStockQuantity(Integer.parseInt(stockQuantity));
        product.setCategory(txtCategory.getText());
        productDAO.addProduct(product);
        loadProducts();
    }

    private void updateProduct() {
        String productId = JOptionPane.showInputDialog(this, "수정할 상품의 고유번호를 입력하세요:");
        if (productId == null || productId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "상품 고유번호를 입력하세요.");
            return;
        }

        String name = txtName.getText();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "도서명을 입력하세요.");
            return;
        }
        String author = txtAuthor.getText();
        if (author.isEmpty()) {
            JOptionPane.showMessageDialog(this, "저자를 입력하세요.");
            return;
        }
        String publisher = txtPublisher.getText();
        if (publisher.isEmpty()) {
            JOptionPane.showMessageDialog(this, "출판사를 입력하세요.");
            return;
        }
        String price = txtPrice.getText();
        if (price.isEmpty()) {
            JOptionPane.showMessageDialog(this, "가격을 입력하세요.");
            return;
        }
        String stockQuantity = txtStockQuantity.getText();
        if (stockQuantity.isEmpty()) {
            JOptionPane.showMessageDialog(this, "재고 수를 입력하세요.");
            return;
        }
        String category = txtCategory.getText();
        if (category.isEmpty()) {
            JOptionPane.showMessageDialog(this, "카테고리를 입력하세요.");
            return;
        }

        Supplier supplier = supplierDAO.getSupplierByName((String) comboSupplierName.getSelectedItem());
        if (supplier == null) {
            JOptionPane.showMessageDialog(this, "존재하지 않는 공급체입니다.");
            return;
        }

        Product product = new Product();
        product.setProductId(productId);
        product.setSID(supplier.getSupplierId());
        product.setPname(txtName.getText());
        product.setAuthor(txtAuthor.getText());
        product.setPublisher(txtPublisher.getText());
        product.setPrice(Double.parseDouble(txtPrice.getText()));
        product.setStockQuantity(Integer.parseInt(txtStockQuantity.getText()));
        product.setCategory(txtCategory.getText());
        productDAO.updateProduct(product);
        loadProducts();
    }

    private void deleteProduct() {
        String productId = JOptionPane.showInputDialog(this, "삭제할 상품의 고유번호를 입력하세요:");
        if (productId == null || productId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "상품 고유번호를 입력하세요.");
            return;
        }

        Product product = productDAO.getProductById(productId);
        if (product == null) {
            JOptionPane.showMessageDialog(this, "존재하지 않는 상품입니다.");
            return;
        }

        productDAO.deleteProduct(productId);
        loadProducts();
    }

    private void searchProduct() {
        String productId = JOptionPane.showInputDialog(this, "검색할 상품의 고유번호를 입력하세요:");
        if (productId == null || productId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "상품 고유번호를 입력하세요.");
            return;
        }
        Product product = productDAO.getProductById(productId);
        if (product != null) {
            Supplier supplier = supplierDAO.getSupplierById(product.getSID());
            String supplierName = (supplier != null) ? supplier.getName() : "Unknown";
            tableModel.setRowCount(0); // Clear existing data
            tableModel.addRow(new Object[]{
                product.getProductId(),
                product.getSID(),
                supplierName,
                product.getPname(),
                product.getAuthor(),
                product.getPublisher(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getCategory()
            });
        } else {
            JOptionPane.showMessageDialog(this, "존재하지 않는 상품입니다.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProductManagementGUI().setVisible(true);
            }
        });
    }
}
