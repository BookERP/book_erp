package src.main.java.com.bookstore.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import src.main.java.com.bookstore.dao.EmployeeDAO;
import src.main.java.com.bookstore.model.Employee;
import src.main.java.com.bookstore.service.LoginService;

public class MyPagePanel extends JFrame {
	private JTable employeeTable;
	private DefaultTableModel tableModel;
	private EmployeeDAO employeeDAO;
	private String EmployeeID;
	
	public MyPagePanel() {
		this.EmployeeID = EmployeeID;
		employeeDAO = new EmployeeDAO();
		setTitle("MyPage");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        tableModel = new DefaultTableModel(new String[]{"Employee ID", "Name", "Position", "Phone", "Email", "HireDate"}, 0);
        employeeTable = new JTable(tableModel);
        add(new JScrollPane(employeeTable), BorderLayout.CENTER);
//        add(new JScrollPane(employeeTable));
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
        loadEmployees();
	}
	
	private void loadEmployees() {
//		List<Employee> employees = employeeDAO.getEmployees();
//		tableModel.setRowCount(0);
//		for (Employee employee : employees) {
//			employeeDAO.setEmployeeID(employee.getEMPLOYEEID());
//			Employee detailedEmployee = (Employee) employeeDAO.getEmployeeByID();
//			if (detailedEmployee != null) {
//				tableModel.addRow(new Object[] {
//					employee.getEMPLOYEEID(),
//					employee.getNAME(),
//					employee.getPosition(),
//					employee.getPhone(),
//					employee.getEmail(),
//					employee.getHireDate()
//				});
//			}
//		}
		String employeeID = LoginService.loggedInEmployeeID;
		employeeDAO.setEmployeeID(employeeID);
		Employee employee = employeeDAO.getEmployeeByID();
		if (employee != null) {
			tableModel.setRowCount(0);
			tableModel.addRow(new Object[] {
				employee.getEMPLOYEEID(),
				employee.getNAME(),
				employee.getPosition(),
				employee.getPhone(),
				employee.getEmail(),
				employee.getHireDate()
			});
		} else {
			JOptionPane.showMessageDialog(this, "No employee found with ID: " + EmployeeID);
		}
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
