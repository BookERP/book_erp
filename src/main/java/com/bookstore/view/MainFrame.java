package main.java.com.bookstore.view;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.java.com.bookstore.service.LoginService;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("ERP");
        setSize(1600, 1200);
        setMenuBar();
        addImageToCenter(); // Add this line to call the method
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensure the application exits when the frame is closed
    }

    private void setMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu management = new JMenu("업무 목록");
        JMenu usermanagement = new JMenu("마이페이지");

        JMenuItem productManagement = new JMenuItem("상품관리");
        JMenuItem customerManagement = new JMenuItem("고객관리");
        JMenuItem hrManagement = new JMenuItem("인사관리");
        JMenuItem exitItem = new JMenuItem("프로그램 종료");
        JMenuItem logout = new JMenuItem("로그아웃");
        JMenuItem myPage = new JMenuItem("회원정보");
        JMenuItem inventory = new JMenuItem("재고관리");

        management.add(productManagement);
        management.add(inventory);
        management.add(customerManagement);
        management.add(hrManagement);
        
        usermanagement.add(myPage);
        usermanagement.add(logout);
        usermanagement.addSeparator();
        usermanagement.add(exitItem);

        jMenuBar.add(management);
        jMenuBar.add(usermanagement);

        setJMenuBar(jMenuBar);
        
        //재고관리
        inventory.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		new InventoryManagementGUI().setVisible(true);
        	}
        });
        
        //마이페이지
        myPage.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		new MyPagePanel().setVisible(true);
        	}
        });
        
        // 상품 관리
        productManagement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProductManagementGUI().setVisible(true);
            }
        });

        // 시스템 종료
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //로그아웃
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); //메인프레임 닫기
                new LoginService().setVisible(true); // 로그인 서비스 열기
            }
        });
    }
    
    private void addImageToCenter() {
        // Load the image
        ImageIcon imageIcon = new ImageIcon("C:/oracle/Project/BookERP/src/main/java/com/bookstore/view/logo02.jpg");
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(650, 700,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newimg);  // transform it back

        JLabel imageLabel = new JLabel(imageIcon);

        // Center the image
        add(imageLabel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
}
