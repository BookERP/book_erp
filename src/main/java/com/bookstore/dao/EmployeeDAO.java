package src.main.java.com.bookstore.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.main.java.com.bookstore.model.Employee;
import src.main.java.com.bookstore.util.ConnectionHelper;

public class EmployeeDAO {
	public List<Employee> getEmployee() {
		List<Employee> employees = new ArrayList<>();
		Connection conn = ConnectionHelper.getConnection("oracle");
		String query = "SELECT EMPLOYEEID, NAME, POSITION, PHONE, EMAIL, HIREDATE from Employee WHERE EMPLOYEEID";
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			String ID = new Employee().getEMPLOYEEID();
			pstmt.setString(1,ID);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Employee employee = new Employee();
				employee.setEMPLOYEEID(rs.getString("EMPLOYEEID"));
				employee.setNAME(rs.getString("NAME"));
				employee.setPosition(rs.getString("POSITION"));
				employee.setPhone(rs.getString("PHONE"));
				employee.setEmail(rs.getString("EMAIL"));
				employee.setHireDate(rs.getDate("HIREDATE"));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}
}
