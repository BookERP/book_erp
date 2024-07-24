package main.java.com.bookstore.model;

import java.util.Date;

public class Customer {
	private String CustomerId;
	private String Cpw;
	private String CName;
	private String CPhone;
	private String CEmail;
	private String CAddress;
	private Date RDate;
	public String getCustomerId() {
		return CustomerId;
	}
	public void setCustomerId(String customerId) {
		this.CustomerId = customerId;
	}
	public String getCpw() {
		return Cpw;
	}
	public void setCpw(String cpw) {
		this.Cpw = cpw;
	}
	public String getCName() {
		return CName;
	}
	public void setCName(String cName) {
		this.CName = cName;
	}
	public String getCPhone() {
		return CPhone;
	}
	public void setCPhone(String cPhone) {
		this.CPhone = cPhone;
	}
	public String getCEmail() {
		return CEmail;
	}
	public void setCEmail(String cEmail) {
		this.CEmail = cEmail;
	}
	public String getCAddress() {
		return CAddress;
	}
	public void setCAddress(String cAddress) {
		this.CAddress = cAddress;
	}
	public Date getRDate() {
		return RDate;
	}
	public void setRDate(Date rDate) {
		this.RDate = rDate;
	}
	@Override
	public String toString() {
		return "Customer [CustomerId=" + CustomerId + ", Cpw=" + Cpw + ", CName=" + CName + ", CPhone=" + CPhone
				+ ", CEmail=" + CEmail + ", CAddress=" + CAddress + ", RDate=" + RDate + "]";
	}
	
	
}
