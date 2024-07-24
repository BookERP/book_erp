package main.java.com.bookstore.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import main.java.com.bookstore.model.MyModel;
import main.java.com.bookstore.util.ConnectionHelper;


public class ErpCRUD extends JFrame implements ActionListener{
	
	// GUI 컴포넌트
	JPanel panWest, panSouth;
	JPanel p1,p2,p3,p4,p5,p6,p7; 
	JTextField txtName, txtPhone, txtEmail, txtAddress, txtRDate, txtCustomerID, txtCpw;
	JButton  btnTotal, btnSearch, btnDel, btnUpdate, btnCancel;
	
	JTable table;
	
	// 상태변수 선언
	private static final int NONE = 0;
	private static final int DELETE = 1;
	private static final int SEARCH = 2;
	private static final int TOTAL = 3;
	private static final int UPDATE = 4;
	int cmd = NONE;
	
	MyModel model;
	
	// GUI 초기화 및 DB 연결
	public ErpCRUD(){
		
		// 패널
		panWest = new JPanel(new GridLayout(7, 0));
		
		p1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p1.add(new JLabel("이름"));
		p1.add(txtName = new JTextField(12));
		panWest.add(p1);
		
		p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p2.add(new JLabel("전화번호"));
		p2.add(txtPhone = new JTextField(12));
		panWest.add(p2);
		
		p3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p3.add(new JLabel("이메일"));
		p3.add(txtEmail = new JTextField(12));
		panWest.add(p3);
		
		p4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p4.add(new JLabel("주소"));
		p4.add(txtAddress = new JTextField(12));
		panWest.add(p4);
		
		p5 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p5.add(new JLabel("가입일자"));
		p5.add(txtRDate = new JTextField(12));
		panWest.add(p5);
		
		p6 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p6.add(new JLabel("고객ID"));
		p6.add(txtCustomerID = new JTextField(12));
		panWest.add(p6);
		
		p7 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p7.add(new JLabel("비밀번호"));
		p7.add(txtCpw = new JTextField(12));
		panWest.add(p7);
		
		add(panWest, "West");
		
		// 버튼
		panSouth = new JPanel();
		panSouth.add(btnTotal= new JButton("전체보기")); 
		panSouth.add(btnSearch= new JButton("검  색"));
		panSouth.add(btnUpdate= new JButton("수  정"));
		panSouth.add(btnDel= new JButton("삭  제"));
		panSouth.add(btnCancel= new JButton("취  소"));
		
		add(panSouth, "South");
		
		// 이벤트 처리 등록
		btnTotal.addActionListener(this);
		btnSearch.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDel.addActionListener(this);
		btnCancel.addActionListener(this);
		
		// 테이블 생성
		add(new JScrollPane(table = new JTable()), "Center");
		
		// 창 닫기, 화면
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1000, 500);
		setVisible(true);
		
		// DB 연결 및 초기 데이터 로드
		dbConnect();
		total();
	}
	
	// DB 연결 설정
	Connection conn;
	Statement stmt;
	PreparedStatement pstmtDelete, pstmtUpdate;
	PreparedStatement pstmtSelect, pstmtSelectScroll;
	PreparedStatement pstmtSearch, pstmtSearchScroll;
	
	// SQL 쿼리
	private String sqlUpdate = "UPDATE customer SET NAME = ?, PHONE = ?, EMAIL = ?, ADDRESS = ?, RDATE = ?, CustomerCpw = ? WHERE CustomerID = ?";
	private String sqlDelete = "delete from customer where CustomerID = ?";
	private String sqlSelect = "select * from customer";
	private String sqlSearch = "select * from customer where name = ?";
	
	// DB 연결 메서드
	public void dbConnect() {
		try {
			conn = ConnectionHelper.getConnection();
			
			pstmtDelete = conn.prepareStatement(sqlDelete);
			pstmtSelect = conn.prepareStatement(sqlSelect);
			pstmtSearch = conn.prepareStatement(sqlSearch);
			pstmtUpdate = conn.prepareStatement(sqlUpdate);
			
			pstmtSelectScroll = conn.prepareStatement(sqlSelect,
																ResultSet.TYPE_SCROLL_SENSITIVE,
																ResultSet.CONCUR_UPDATABLE);
			pstmtSearchScroll = conn.prepareStatement(sqlSearch,
																ResultSet.TYPE_SCROLL_SENSITIVE,
																ResultSet.CONCUR_UPDATABLE);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 버튼 이벤트 처리
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if( obj == btnUpdate ){
			if( cmd != UPDATE ){
				setText(UPDATE);
				return;
			}
			setTitle(e.getActionCommand());
			update();
			
		}else if( obj == btnDel ){
			if( cmd != DELETE ){
				setText(DELETE);
				return;
			}
			setTitle(e.getActionCommand());
			try {
				del();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}else if( obj == btnSearch ){
			if( cmd != SEARCH ){
				setText(SEARCH);
				return;
			}
			setTitle(e.getActionCommand());
			search();
			
		}else if( obj == btnTotal ){
			setTitle(e.getActionCommand());
			total();
		}
		setText(NONE);
		init();
	}

	// 수정
	 public void update() {
	      total();
	      
	      String strName = txtName.getText();
	      String strPhone = txtPhone.getText();
	      String strMail = txtEmail.getText();
	      String strAddress = txtAddress.getText();
	      String strId = txtCustomerID.getText();
	      String strPwd = txtCpw.getText();
	      
	      if(strName.isEmpty() && strPhone.isEmpty() && strMail.isEmpty() && strAddress.isEmpty() && strPwd.isEmpty()) {
	         JOptionPane.showMessageDialog(null, "경고: 수정하실 ID를 입력 후 수정 사항을 입력해주세요.");
	         return;
	      }
	      
	      switch (JOptionPane.showConfirmDialog(null,
	            "( ID ' " + strId + " '님의 고객 정보 )", "수정하시겠습니까? ",
	            JOptionPane.YES_NO_OPTION)) {

	         case 0: // 확인
	            break;
	         case 1: // 아니오
	            return;
	      }
	      
	      String strRdate = "";
	      try {  	  
	    	  String sqlSelect = "SELECT Name, Phone, Email, Address, RDATE, CustomerCpw FROM customer WHERE CustomerID = ?";
	          PreparedStatement pstmtSelect = conn.prepareStatement(sqlSelect);
	          pstmtSelect.setString(1, strId);
	          ResultSet rs = pstmtSelect.executeQuery();
	          if (rs.next()) {
	        	  if (strName.isEmpty()) {
	                  strName = rs.getString("Name");
	              }
	              if (strPhone.isEmpty()) {
	                  strPhone = rs.getString("Phone");
	              }
	              if (strMail.isEmpty()) {
	                  strMail = rs.getString("Email");
	              }
	              if (strAddress.isEmpty()) {
	                  strAddress = rs.getString("Address");
	              }
	              if (strPwd.isEmpty()) {
	                  strPwd = rs.getString("CustomerCpw");
	              }
	        	  
	              java.sql.Date dateRdate = rs.getDate("RDATE");
	              if (dateRdate != null) {
	                  strRdate = dateRdate.toString();
	              }
	          }
	      } catch (SQLException e) {
	          e.printStackTrace();
	      }
	      
	      try {
	         pstmtUpdate.setString(1, strName);
	         pstmtUpdate.setString(2, strPhone);
	         pstmtUpdate.setString(3, strMail);
	         pstmtUpdate.setString(4, strAddress);
	         pstmtUpdate.setDate(5, Date.valueOf(strRdate));
	         pstmtUpdate.setString(6, strPwd);
	         pstmtUpdate.setString(7, strId);
	         
	         pstmtUpdate.executeUpdate();
	      } catch (Exception e1) {
	         e1.printStackTrace();
	      }
	      JOptionPane.showMessageDialog(null, "수정되었습니다.");
	      total();
	   }
	
	// 삭제
	public void del() throws SQLException {
	      total();
	      String strID = txtCustomerID.getText();
	      
	      if(strID.isEmpty()) {
	         JOptionPane.showMessageDialog(null, "경고: 삭제할 아이디를 입력해주세요");
	         return;
	      }
	      
	      switch (JOptionPane.showConfirmDialog(null,
	            "( ID ' " + strID + " '님 고객 정보)", "삭제하시겠습니까? ",
	            JOptionPane.YES_NO_OPTION)) {

	         case 0: // 확인
	            break;
	         case 1: // 아니오
	            return;
	      }
	      
	      try {
	         pstmtDelete.setString(1, strID);
	         pstmtDelete.executeUpdate();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      
	      JOptionPane.showMessageDialog(null, "삭제되었습니다.");
	      
	      total();
	   }
	
	// 검색
	public void search() {
		String strName = txtName.getText();
		
		if( strName.length() < 1 ) {
			JOptionPane.showMessageDialog(null, "경고: 이름 입력은 필수 입니다. 입력해주세요.");
			return;
		}
		
		try {
			pstmtSearchScroll.setString(1, strName);
			ResultSet rsScroll = pstmtSearchScroll.executeQuery();
			pstmtSearch.setString(1, strName);
			ResultSet rs = pstmtSearch.executeQuery();
			
			if( model == null ) model = new MyModel();
			
			model.getRowCount(rsScroll);
			model.setData(rs);
			
			table.setModel(new DefaultTableModel(model.data, model.columnName));
			table.updateUI();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 전체보기
	private void total() {
		try {
			ResultSet rs = pstmtSelect.executeQuery();
			ResultSet rsScroll = pstmtSelectScroll.executeQuery();
			
			if( model == null ) model = new MyModel();
			
			model.getRowCount(rsScroll);
			model.setData(rs);
			
			table.setModel(new DefaultTableModel(model.data, model.columnName));
			table.updateUI();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 초기화 메서드
	private void init() {
		txtName.setText("");		txtName.setEditable(false);
		txtPhone.setText("");		txtPhone.setEditable(false);
		txtEmail.setText("");		txtEmail.setEditable(false);
		txtAddress.setText("");		txtAddress.setEditable(false);
		txtRDate.setText("");		txtRDate.setEditable(false);
		txtCustomerID.setText("");	txtCustomerID.setEditable(false);
		txtCpw.setText("");			txtCpw.setEditable(false);
	}

	// 입력 필드 활성화 설정
	private void setText(int command) {
		switch(command){
			case DELETE :
				txtCustomerID.setEditable(true);
				break;
			case SEARCH :
				txtName.setEditable(true);
				break;
			case UPDATE :
				 txtName.setEditable(true);
		         txtPhone.setEditable(true);
		         txtEmail.setEditable(true);
		         txtAddress.setEditable(true);
		         txtRDate.setEditable(false);
		         txtCustomerID.setEditable(true);
		         txtCpw.setEditable(true);
		         break;
		}
		
		setButton(command);
	}

	// 버튼 활성화 설정
	private void setButton(int command) {
		btnTotal.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnDel.setEnabled(false);
		btnSearch.setEnabled(false);
		
		switch(command){
			case DELETE :
				btnDel.setEnabled(true);
				cmd = DELETE;
				break;
				
			case SEARCH :
				btnSearch.setEnabled(true);
				cmd = SEARCH;
				break;
				
			case UPDATE :
	            btnUpdate.setEnabled(true);
	            cmd = UPDATE;
	            break;
	            
			case TOTAL :
				btnTotal.setEnabled(true);
				cmd = TOTAL;
				break;
				
			case NONE :
				btnTotal.setEnabled(true);
				btnUpdate.setEnabled(true);
				btnDel.setEnabled(true);
				btnSearch.setEnabled(true);
				btnCancel.setEnabled(true);
				cmd = NONE;
				break;
		}	
	}

	public static void main(String[] args) {
		new ErpCRUD();
	}
}




