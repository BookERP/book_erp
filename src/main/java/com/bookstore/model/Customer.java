package main.java.com.bookstore.model;

import java.util.Date;

public class Customer {
	private String CustomerId;
	private String CustomerCpw;
	private String Name;
	private String Phone;
	private String Email;
	private String Address;
	private Date RDate;
	
	
	public String getCustomerId() {
		return CustomerId;
	}
	public void setCustomerId(String customerId) {
		CustomerId = customerId;
	}
	public String getCustomerCpw() {
		return CustomerCpw;
	}
	public void setCustomerCpw(String customerCpw) {
		CustomerCpw = customerCpw;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
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
	public Date getRDate() {
		return RDate;
	}
	public void setRDate(Date rdate) {
		RDate = rdate;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	@Override
	public String toString() {
		return "Customer [CustomerId=" + CustomerId + ", CustomerCpw=" + CustomerCpw + ", Name=" + Name + ", Phone="
				+ Phone + ", Email=" + Email + ", RDate=" + RDate + ", Address=" + Address + "]";
	}
}
