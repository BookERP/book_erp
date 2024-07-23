<<<<<<< HEAD
package main.java.com.bookstore.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.table.AbstractTableModel;

public class MyModel extends AbstractTableModel { //격자 모양 만들기
	
	public Object[][] data;
	public String[] columnName;
	int rows, cols; // 레코드 행과 열의 개수를 저장하는 변수

	@Override
	public int getRowCount() { // 레코드 개수
		return data.length;
	}

	@Override
	public int getColumnCount() { // 피드(열)의 개수
		return columnName.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
	
	public void getRowCount(ResultSet rsScroll) { // user method
		try {
			rsScroll.last(); // 레코드의 마지막 행으로 커서 이동
			rows = rsScroll.getRow();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// DB에 저장된 데이터 채움
	public void setData(ResultSet rs) {
		try {
			String title;
			// 데이터에 대한 정보 얻어옴
			ResultSetMetaData rsmd = rs.getMetaData();
			cols = rsmd.getColumnCount(); // 열의 개수 얻어옴.
			columnName = new String[cols];
			
			for(int i = 0; i < cols; i++) {
				columnName[i] = rsmd.getColumnName(i+1); // 데이터베이스 필드 번호는 1부터 시작함
			}
			
			data = new Object[rows][cols];
			int r = 0;
			
			while( rs.next() ) {
				for(int i = 0; i < columnName.length; i++) {
					if(i != 1) data[r][i] = rs.getObject(i + 1); //varchar2 type
					else data[r][i] = rs.getObject(i + 1); // number type
				} // for end
				r++;
			} // while end
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
=======
package bookERP;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.table.AbstractTableModel;

public class MyModel extends AbstractTableModel { //격자 모양 만들기
	
	Object[][] data;
	String[] columnName;
	int rows, cols; // 레코드 행과 열의 개수를 저장하는 변수

	@Override
	public int getRowCount() { // 레코드 개수
		return data.length;
	}

	@Override
	public int getColumnCount() { // 피드(열)의 개수
		return columnName.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
	
	public void getRowCount(ResultSet rsScroll) { // user method
		try {
			rsScroll.last(); // 레코드의 마지막 행으로 커서 이동
			rows = rsScroll.getRow();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// DB에 저장된 데이터 채움
	public void setData(ResultSet rs) {
		try {
			String title;
			// 데이터에 대한 정보 얻어옴
			ResultSetMetaData rsmd = rs.getMetaData();
			cols = rsmd.getColumnCount(); // 열의 개수 얻어옴.
			columnName = new String[cols];
			
			for(int i = 0; i < cols; i++) {
				columnName[i] = rsmd.getColumnName(i+1); // 데이터베이스 필드 번호는 1부터 시작함
			}
			
			data = new Object[rows][cols];
			int r = 0;
			
			while( rs.next() ) {
				for(int i = 0; i < columnName.length; i++) {
					if(i != 1) data[r][i] = rs.getObject(i + 1); //varchar2 type
					else data[r][i] = rs.getObject(i + 1); // number type
				} // for end
				r++;
			} // while end
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
>>>>>>> 3f999baad5a585b7e8accd4014255711afa8e554
