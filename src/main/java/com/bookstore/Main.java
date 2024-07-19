package main.java.com.bookstore;

import main.java.com.bookstore.service.LoginService;
import main.java.com.bookstore.view.ProductManagementGUI;

//<<<<<<< HEAD
//public class Main{
//	public static void main(String[] args) {
//		LoginService ls = new LoginService();
//		ls.main(args);
//=======
import javax.swing.*;

public class Main extends JFrame{
	Main() {
		setTitle("ERP");
		setSize(1600,1200);
		setMenuBar();
		setVisible(true);
	}

	private void setMenuBar() {
		JMenuBar jMenuBar = new JMenuBar();
		JMenu management = new JMenu("management");

		management.add(new JMenuItem("상품 관리"));
		management.add(new JMenuItem("고객 관리"));
		management.add(new JMenuItem("인사 관리"));
		management.addSeparator();
		management.add(new JMenuItem("Exit"));

		jMenuBar.add(management);
		jMenuBar.add(new JMenu("Edit"));
		jMenuBar.add(new JMenu("샘플"));
		jMenuBar.add(new JMenu("샘플"));
		jMenuBar.add(new JMenu("샘플"));
		setJMenuBar(jMenuBar);
	}

	public static void main(String[] args) {


		new Main();

		ProductManagementGUI pmg = new ProductManagementGUI();
		pmg.main(args);
	}
}