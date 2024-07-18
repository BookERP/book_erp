package src.main.java.com.bookstore.model;

import java.util.Date;

public class Employee {
	private String EMPLOYEEID;
	private String NAME;
	private String Position;
	private String Phone;
	private String Email;
	private Date HireDate;
	public String getEMPLOYEEID() {
		return EMPLOYEEID;
	}
	public void setEMPLOYEEID(String eMPLOYEEID) {
		EMPLOYEEID = eMPLOYEEID;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getPosition() {
		return Position;
	}
	public void setPosition(String position) {
		Position = position;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public Date getHireDate() {
		return HireDate;
	}
	public void setHireDate(Date hireDate) {
		HireDate = hireDate;
	}
	@Override
	public String toString() {
		return "Employee [EMPLOYEEID=" + EMPLOYEEID + ", NAME=" + NAME + ", Position=" + Position + ", Phone=" + Phone
				+ ", Email=" + Email + ", HireDate=" + HireDate + "]";
	}
}
