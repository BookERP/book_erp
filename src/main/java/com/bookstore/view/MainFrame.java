package main.java.com.bookstore.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.java.com.bookstore.service.LoginService;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("ERP");
        setSize(1600, 1200);
        setMenuBar();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensure the application exits when the frame is closed
    }

    private void setMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu management = new JMenu("업무 목록");
        JMenu usermanagement = new JMenu("마이페이지");

        JMenuItem productManagement = new JMenuItem("상품 관리");
        JMenuItem customerManagement = new JMenuItem("고객 관리");
        JMenuItem hrManagement = new JMenuItem("인사 관리");
        JMenuItem exitItem = new JMenuItem("프로그램 종료");
        JMenuItem logout = new JMenuItem("로그아웃");

        management.add(productManagement);
        management.add(customerManagement);
        management.add(hrManagement);
        
        usermanagement.add(logout);
        usermanagement.addSeparator();
        usermanagement.add(exitItem);

        jMenuBar.add(management);
        jMenuBar.add(usermanagement);

        setJMenuBar(jMenuBar);
        
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
}
