package src.main.java.com.bookstore;

<<<<<<< HEAD
import src.main.java.com.bookstore.service.LoginService;

public class Main {
	public static void main(String[] args) {
		LoginService ls = new LoginService();
		ls.main(args);
=======
import main.java.com.bookstore.service.LoginService;
import main.java.com.bookstore.view.ProductManagementGUI;

public class Main {
	public static void main(String[] args) {
//		LoginService ls = new LoginService();
//		ls.main(args);
		ProductManagementGUI pmg = new ProductManagementGUI();
		pmg.main(args);
>>>>>>> 1dfa6bbc17224b955e85a46774d317d7b18704ab
	}
}
