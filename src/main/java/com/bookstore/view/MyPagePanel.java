package src.main.java.com.bookstore.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import src.main.java.com.bookstore.dao.EmployeeDAO;
import src.main.java.com.bookstore.model.User;

public class MyPagePanel extends JFrame {
	private JTable employeeTable;
	private DefaultTableModel tableModel;
	private EmployeeDAO employeeDAO;
	
	public MyPagePanel() {
		setTitle("MyPage");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        tableModel = new DefaultTableModel(new String[]{"Employee ID", "Name", "Position", "Phone", "Email", "HireDate"}, 0);
        employeeTable = new JTable(tableModel);
        add(new JScrollPane(employeeTable), BorderLayout.CENTER);
//        try {
//        	employeeDAO = new CustomerDAO();
//			User user = customerDAO.selectMyUser("test");
//			
//			add(new JLabel("ID: " + user.getUserId()));
//	        add(new JLabel("Name: " + user.getUsername()));
//	        add(new JLabel("Phone: " + user.getUserId()));
//	        add(new JLabel("Email: " + user.getEmail()));
//	        add(new JLabel("Address: " + user.getAddress()));
//		} catch(SQLException e) {
//			e.printStackTrace();
//		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MyPagePanel().setVisible(true);
            }
        });
	}
}
