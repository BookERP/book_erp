package main.java.com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.com.bookstore.model.Employee;
import main.java.com.bookstore.util.ConnectionHelper;

public class EmployeeDAO {
	
	private Connection conn;

    public EmployeeDAO() {
        conn = ConnectionHelper.getConnection();
    }
	private String employeeID;

	public List<Employee> getEmployees() {
		List<Employee> employees = new ArrayList<>();
		String query = "SELECT EMPLOYEEID, ENAME, POSITION, EPHONE, EEMAIL, HIREDATE FROM Employee";

		try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setEMPLOYEEID(rs.getString("EMPLOYEEID"));
				employee.setNAME(rs.getString("EMPLOYEEID"));
				employee.setPosition(rs.getString("POSITION"));
				employee.setPhone(rs.getString("EPHONE"));
				employee.setEmail(rs.getString("EEMAIL"));
				employee.setHireDate(rs.getDate("HIREDATE"));
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}

	public Employee getEmployeeByID() {
		Employee employee = null;
		String query = "SELECT EMPLOYEEID, ENAME, POSITION, EPHONE, EEMAIL, HIREDATE from Employee WHERE EMPLOYEEID = ?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, this.employeeID);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
//					Employee employee = new Employee();
//					String ID = employee.getEMPLOYEEID();
					employee = new Employee();
					employee.setEMPLOYEEID(rs.getString("EMPLOYEEID"));
					employee.setNAME(rs.getString("ENAME"));
					employee.setPosition(rs.getString("POSITION"));
					employee.setPhone(rs.getString("EPHONE"));
					employee.setEmail(rs.getString("EEMAIL"));
					employee.setHireDate(rs.getDate("HIREDATE"));
//					employees.add(employee);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
//		return employees;
		return employee;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
}
