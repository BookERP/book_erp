package src.main.java.com.bookstore;

import src.main.java.com.bookstore.service.LoginService;
import src.main.java.com.bookstore.view.ProductManagementGUI;

public class Main{
	public static void main(String[] args) {
		LoginService ls = new LoginService();
		ls.main(args);
	}
}