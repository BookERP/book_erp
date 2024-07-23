package src.main.java.com.bookstore.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import src.main.java.com.bookstore.dao.EmployeeDAO;
import src.main.java.com.bookstore.model.Employee;
import src.main.java.com.bookstore.service.LoginService;

public class MyPagePanel extends JFrame {
	private EmployeeDAO employeeDAO;
	private String EmployeeID;
	
	public MyPagePanel() {
		this.EmployeeID = LoginService.loggedInEmployeeID;
		employeeDAO = new EmployeeDAO();
		setTitle("MyPage");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        loadEmployees();
	}
	
	private void loadEmployees() {
		employeeDAO.setEmployeeID(EmployeeID);
		Employee employee = employeeDAO.getEmployeeByID();
		if (employee != null) {
			JPanel infoPanel = new JPanel(new GridLayout(6, 2, 10, 10));
//            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
            
            infoPanel.add(createLabel("직원 ID : "));
            infoPanel.add(createLabel(employee.getEMPLOYEEID()));
            infoPanel.add(createLabel("직원 이름 : "));
            infoPanel.add(createLabel(employee.getNAME()));
            infoPanel.add(createLabel("직급 : "));
            infoPanel.add(createLabel(employee.getPosition()));
            infoPanel.add(createLabel("직원 연락처 : "));
            infoPanel.add(createLabel(employee.getPhone()));
            infoPanel.add(createLabel("직원 이메일 : "));
            infoPanel.add(createLabel(employee.getEmail()));
            infoPanel.add(createLabel("입사일자 : "));
            infoPanel.add(createLabel(employee.getHireDate().toString()));
            
            add(infoPanel, BorderLayout.CENTER);
		} else {
			JOptionPane.showMessageDialog(this, "No employee found with ID: " + EmployeeID);
		}
	}
	
	private JLabel createLabel(String text) {
		JLabel label = new JLabel(text);
        label.setBorder(BorderFactory.createCompoundBorder(
        	new LineBorder(Color.BLACK, 1), // 테두리 추가
            new EmptyBorder(5, 5, 5, 5) // 라벨에 여백 추가
        ));
        label.setHorizontalAlignment(SwingConstants.CENTER); // 텍스트 가운데 정렬
        return label;
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