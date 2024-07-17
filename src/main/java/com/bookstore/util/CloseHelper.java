<<<<<<< HEAD
package src.main.java.com.bookstore.util;
=======
package main.java.com.bookstore.util;
>>>>>>> 1dfa6bbc17224b955e85a46774d317d7b18704ab
/*
 * if(rs != null) try{rs.close();}catch(SQLException e){ e.printStackTrace(); }
 * if(stmt != null) try{stmt.close();}catch(SQLException e){ e.printStackTrace(); }
 * if(pstmt != null) try{pstmt.close();}catch(SQLException e){ e.printStackTrace(); }
 * if(conn != null) try{conn.close();}catch(SQLException e){ e.printStackTrace(); }
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CloseHelper {
	public static void close(Connection conn) {
		 if(conn != null) try{conn.close();}catch(SQLException e){ e.printStackTrace(); }

	}
	
	public static void close(Statement stmt) {
		 if(stmt != null) try{stmt.close();}catch(SQLException e){ e.printStackTrace(); }

	}
	
	public static void close(PreparedStatement pstmt) {
		 if(pstmt != null) try{pstmt.close();}catch(SQLException e){ e.printStackTrace(); }

	}
	
	public static void close(ResultSet rs) {
		 if(rs != null) try{rs.close();}catch(SQLException e){ e.printStackTrace(); }

	}
}