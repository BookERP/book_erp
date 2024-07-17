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
    private JTextField txtProductId, txtName, txtAuthor, txtPublisher, txtPrice, txtStockQuantity, txtCategory;
    private JComboBox<String> comboSupplierId;
    private JTable productTable;
    private DefaultTableModel tableModel;
    private ProductDAO productDAO;
    private SupplierDAO supplierDAO;

    public ProductManagementGUI() {
        productDAO = new ProductDAO();
        supplierDAO = new SupplierDAO();
        setTitle("Product Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(8, 2));
        inputPanel.add(new JLabel("Product ID:"));
        txtProductId = new JTextField();
        inputPanel.add(txtProductId);

        inputPanel.add(new JLabel("Supplier ID:"));
        comboSupplierId = new JComboBox<>();
        inputPanel.add(comboSupplierId);
        loadSuppliers();

        inputPanel.add(new JLabel("Name:"));
        txtName = new JTextField();
        inputPanel.add(txtName);

        inputPanel.add(new JLabel("Author:"));
        txtAuthor = new JTextField();
        inputPanel.add(txtAuthor);

        inputPanel.add(new JLabel("Publisher:"));
        txtPublisher = new JTextField();
        inputPanel.add(txtPublisher);

        inputPanel.add(new JLabel("Price:"));
        txtPrice = new JTextField();
        inputPanel.add(txtPrice);

        inputPanel.add(new JLabel("Stock Quantity:"));
        txtStockQuantity = new JTextField();
        inputPanel.add(txtStockQuantity);

        inputPanel.add(new JLabel("Category:"));
        txtCategory = new JTextField();
        inputPanel.add(txtCategory);

        add(inputPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"Product ID", "Supplier ID", "Name", "Author", "Publisher", "Price", "Stock Quantity", "Category"}, 0);
        productTable = new JTable(tableModel);
        add(new JScrollPane(productTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("Add");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);

        add(buttonPanel, BorderLayout.SOUTH);

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

        loadProducts();
    }

    private void loadSuppliers() {
        List<Supplier> suppliers = supplierDAO.getAllSuppliers();
        for (Supplier supplier : suppliers) {
            comboSupplierId.addItem(supplier.getSupplierId());
        }
    }

    private void loadProducts() {
        List<Product> products = productDAO.getAllProducts();
        tableModel.setRowCount(0); // Clear existing data
        for (Product product : products) {
            tableModel.addRow(new Object[]{
                product.getProductId(),
                product.getSupplierid(),
                product.getName(),
                product.getAuthor(),
                product.getPublisher(),
                product.getPrice(),
                product.getStuckQuantity(),
                product.getCategory()
            });
        }
    }

    private void addProduct() {
        Product product = new Product();
        product.setProductId(txtProductId.getText());
        product.setSupplierid((String) comboSupplierId.getSelectedItem());
        product.setName(txtName.getText());
        product.setAuthor(txtAuthor.getText());
        product.setPublisher(txtPublisher.getText());
        product.setPrice(Double.parseDouble(txtPrice.getText()));
        product.setStuckQuantity(Integer.parseInt(txtStockQuantity.getText()));
        product.setCategory(txtCategory.getText());
        productDAO.addProduct(product);
        loadProducts();
    }

    private void updateProduct() {
        Product product = new Product();
        product.setProductId(txtProductId.getText());
        product.setSupplierid((String) comboSupplierId.getSelectedItem());
        product.setName(txtName.getText());
        product.setAuthor(txtAuthor.getText());
        product.setPublisher(txtPublisher.getText());
        product.setPrice(Double.parseDouble(txtPrice.getText()));
        product.setStuckQuantity(Integer.parseInt(txtStockQuantity.getText()));
        product.setCategory(txtCategory.getText());
        productDAO.updateProduct(product);
        loadProducts();
    }

    private void deleteProduct() {
        String productId = txtProductId.getText();
        productDAO.deleteProduct(productId);
        loadProducts();
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
