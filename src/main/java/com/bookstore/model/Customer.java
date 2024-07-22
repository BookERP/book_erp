package main.java.com.bookstore.model;

import java.util.Date;

public class Customer {
	
	private String customerId;
	private String customerCpw;
	private String customerName;
	private String customerPhone;
	private String customerEmail;
	private String customerAddress;
	private Date customerRdate;
	
//	public Customer(String strId, String strCpw, String strName, String strPhone, String strEmail, String strAddress, Date strRdate) {
//		this.customerId = strId;
//		this.customerCpw = strCpw;
//		this.customerName = strName;
//		this.customerPhone = strPhone;
//		this.customerEmail = strEmail;
//		this.customerAddress = strAddress;
//		this.customerRdate = strRdate;
//	}

	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerCpw() {
		return customerCpw;
	}
	public void setCustomerCpw(String customerCpw) {
		this.customerCpw = customerCpw;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public Date getCustomerRdate() {
		return customerRdate;
	}
	public void setCustomerRdate(Date customerRdate) {
		this.customerRdate = customerRdate;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerCpw=" + customerCpw + ", customerName=" + customerName
				+ ", customerPhone=" + customerPhone + ", customerEmail=" + customerEmail + ", customerAddress="
				+ customerAddress + ", customerRdate=" + customerRdate + "]";
	}
	
	
	
	
}
