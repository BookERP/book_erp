package main.java.com.bookstore.view;

import main.java.com.bookstore.dao.SupplierDAO;
import main.java.com.bookstore.model.Supplier;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SupplierManagementGUI extends JFrame {
    private JTextField txtName, txtPhone, txtEmail, txtAddress;
    private JTable supplierTable;
    private DefaultTableModel tableModel;
    private SupplierDAO supplierDAO;

    public SupplierManagementGUI() {
        supplierDAO = new SupplierDAO();
        setTitle("공급업체 관리");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        
        inputPanel.add(new JLabel("이름:"));
        txtName = new JTextField();
        inputPanel.add(txtName);

        inputPanel.add(new JLabel("전화번호:"));
        txtPhone = new JTextField();
        inputPanel.add(txtPhone);

        inputPanel.add(new JLabel("이메일:"));
        txtEmail = new JTextField();
        inputPanel.add(txtEmail);

        inputPanel.add(new JLabel("주소:"));
        txtAddress = new JTextField();
        inputPanel.add(txtAddress);

        add(inputPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"공급업체 고유번호", "이름", "전화번호", "이메일", "주소"}, 0);
        supplierTable = new JTable(tableModel);
        add(new JScrollPane(supplierTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("추가");
        JButton btnUpdate = new JButton("수정");
        JButton btnDelete = new JButton("삭제");
        JButton btnLoad = new JButton("목록 불러오기");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnLoad);

        add(buttonPanel, BorderLayout.SOUTH);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSupplier();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSupplier();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSupplier();
            }
        });

        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadSuppliers();
            }
        });

        loadSuppliers();
    }

    private void loadSuppliers() {
        SwingWorker<List<Supplier>, Void> worker = new SwingWorker<List<Supplier>, Void>() {
            @Override
            protected List<Supplier> doInBackground() throws Exception {
                return supplierDAO.getAllSuppliers();
            }

            @Override
            protected void done() {
                try {
                    List<Supplier> suppliers = get();
                    tableModel.setRowCount(0); // Clear existing data
                    for (Supplier supplier : suppliers) {
                        tableModel.addRow(new Object[]{
                            supplier.getSupplierId(),
                            supplier.getName(),
                            supplier.getPhone(),
                            supplier.getEmail(),
                            supplier.getAddress()
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }

    private void addSupplier() {
        Supplier supplier = new Supplier();
        supplier.setName(txtName.getText());
        supplier.setPhone(txtPhone.getText());
        supplier.setEmail(txtEmail.getText());
        supplier.setAddress(txtAddress.getText());
        supplierDAO.addSupplier(supplier);
        loadSuppliers();
    }

    private void updateSupplier() {
        String supplierId = JOptionPane.showInputDialog(this, "수정할 공급업체의 고유번호를 입력하세요:");
        if (supplierId == null || supplierId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "공급업체 고유번호를 입력하세요.");
            return;
        }

        Supplier supplier = new Supplier();
        supplier.setSupplierId(supplierId);
        supplier.setName(txtName.getText());
        supplier.setPhone(txtPhone.getText());
        supplier.setEmail(txtEmail.getText());
        supplier.setAddress(txtAddress.getText());
        supplierDAO.updateSupplier(supplier);
        loadSuppliers();
    }

    private void deleteSupplier() {
        String supplierId = JOptionPane.showInputDialog(this, "삭제할 공급업체의 고유번호를 입력하세요:");
        if (supplierId != null && !supplierId.isEmpty()) {
            supplierDAO.deleteSupplier(supplierId);
            loadSuppliers();
        } else {
            JOptionPane.showMessageDialog(this, "공급업체 고유번호를 입력하세요.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SupplierManagementGUI().setVisible(true);
            }
        });
    }
}
