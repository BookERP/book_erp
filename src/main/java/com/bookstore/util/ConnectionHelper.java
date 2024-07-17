package main.java.com.bookstore.util;

// ("jdbc:oracle:thin:@DB이름_medium?TNS_ADMIN=지갑폴더경로",UserID,UserPW); 클라우드 DB 설정
//, "ADMIN", "Madwolves9810!"
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
	//함수 (접근자 : public , static)
	public static Connection getConnection(String dsn) {
		Connection conn = null;	
		try {
			if(dsn.equalsIgnoreCase("mysql")){
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SampleDB","sozoo","mysql");
			}
				else if(dsn.equalsIgnoreCase("oracle")) {
				
				Class.forName("oracle.jdbc.OracleDriver");

				conn = DriverManager.getConnection("jdbc:oracle:thin:@bookerpmsa_high?TNS_ADMIN=C:/oracle/Project/BookERP/src/main/java/com/bookstore/wallet/Wallet_BookERPMSA", "ADMIN", "Madwolves9810!");

				System.out.println("connection sucess!!");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			return conn;
		}
	}
}