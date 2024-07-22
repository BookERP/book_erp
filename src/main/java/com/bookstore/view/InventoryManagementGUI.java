package main.java.com.bookstore.view;

import main.java.com.bookstore.DAO.InventoryDAO;
import main.java.com.bookstore.DAO.ProductDAO;
import main.java.com.bookstore.model.Inventory;
import main.java.com.bookstore.model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InventoryManagementGUI extends JFrame {
    private JComboBox<String> comboProductId;
    private JTextField txtProductName, txtCurrentQuantity, txtLocation;
    private JTable inventoryTable;
    private DefaultTableModel tableModel;
    private InventoryDAO inventoryDAO;
    private ProductDAO productDAO;

    public InventoryManagementGUI() {
        inventoryDAO = new InventoryDAO();
        productDAO = new ProductDAO();
        setTitle("재고 관리");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        
        inputPanel.add(new JLabel("제품 고유번호"));
        comboProductId = new JComboBox<>();
        loadProducts();
        comboProductId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadProductDetails();
            }
        });
        inputPanel.add(comboProductId);

        inputPanel.add(new JLabel("도서명"));
        txtProductName = new JTextField();
        txtProductName.setEditable(false);
        inputPanel.add(txtProductName);

        inputPanel.add(new JLabel("재고 수"));
        txtCurrentQuantity = new JTextField();
        txtCurrentQuantity.setEditable(false); // Prevent manual input
        inputPanel.add(txtCurrentQuantity);

        inputPanel.add(new JLabel("적재 장소"));
        txtLocation = new JTextField();
        inputPanel.add(txtLocation);

        add(inputPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"재고 고유번호", "제품 고유번호", "도서명", "재고 수", "적재 장소"}, 0);
        inventoryTable = new JTable(tableModel);
        add(new JScrollPane(inventoryTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("정보 추가");
        JButton btnDel = new JButton("재고 삭제");
        JButton btnLoad = new JButton("재고 목록 불러오기");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnDel);
        buttonPanel.add(btnLoad);

        add(buttonPanel, BorderLayout.SOUTH);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addInventory();
            }
        });

        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadInventories();
            }
        });
        
        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteInventory();
            }
        });

        loadInventories();
    }

    private void loadProducts() {
        List<Product> products = productDAO.getAllProducts();
        for (Product product : products) {
            comboProductId.addItem(product.getProductId());
        }
    }
    
    private void deleteInventory() {
        String inventoryId = JOptionPane.showInputDialog(this, "삭제할 재고의 고유번호를 입력하세요:");
        if (inventoryId == null || inventoryId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "재고 고유번호를 입력하세요.");
            return;
        }
        inventoryDAO.deleteInventory(inventoryId);
        loadInventories();
    }

    private void loadProductDetails() {
        String productId = (String) comboProductId.getSelectedItem();
        if (productId != null) {
            Product product = productDAO.getProductById(productId);
            if (product != null) {
                txtProductName.setText(product.getName());
                txtCurrentQuantity.setText(String.valueOf(product.getStockQuantity()));
            }
        }
    }

    private void loadInventories() {
        List<Inventory> inventories = inventoryDAO.getAllInventories();
        tableModel.setRowCount(0); // Clear existing data
        for (Inventory inventory : inventories) {
            Product product = productDAO.getProductById(inventory.getProductId());
            String productName = (product != null) ? product.getName() : "Unknown";
            tableModel.addRow(new Object[]{
                inventory.getInventoryId(),
                inventory.getProductId(),
                productName,
                inventory.getCurrentQuantity(),
                inventory.getLocation()
            });
        }
    }

    private void addInventory() {
        String productId = (String) comboProductId.getSelectedItem();
        if (productId == null || productId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Product ID를 선택하세요.");
            return;
        }
        String currentQuantity = txtCurrentQuantity.getText();
        String location = txtLocation.getText();
        if (location.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Location을 입력하세요.");
            return;
        }

        Inventory inventory = new Inventory();
        inventory.setInventoryId(inventoryDAO.getNextInventoryId()); // 자동으로 Inventory ID 생성
        inventory.setProductId(productId);
        inventory.setCurrentQuantity(Integer.parseInt(currentQuantity));
        inventory.setLocation(location);
        inventoryDAO.addInventory(inventory);
        loadInventories();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InventoryManagementGUI().setVisible(true);
            }
        });
    }
}
