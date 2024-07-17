package main.java.com.bookstore;

import main.java.com.bookstore.service.LoginService;
import main.java.com.bookstore.view.ProductManagementGUI;

public class Main {
	public static void main(String[] args) {
//		LoginService ls = new LoginService();
//		ls.main(args);
		ProductManagementGUI pmg = new ProductManagementGUI();
		pmg.main(args);
	}
}
