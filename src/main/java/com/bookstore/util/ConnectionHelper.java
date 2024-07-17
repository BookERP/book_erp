<<<<<<< HEAD
package src.main.java.com.bookstore.util;
=======
package main.java.com.bookstore.util;
>>>>>>> 1dfa6bbc17224b955e85a46774d317d7b18704ab

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
<<<<<<< HEAD
				conn = DriverManager.getConnection("jdbc:oracle:thin:@bookerpmsa_high?TNS_ADMIN=C:/Users/user/git/BookERP/src/main/java/com/bookstore/wallet/Wallet_BookERPMSA", "ADMIN", "Madwolves9810!");
=======

				conn = DriverManager.getConnection("jdbc:oracle:thin:@bookerpmsa_high?TNS_ADMIN=C:/oracle/Project/BookERP/src/main/java/com/bookstore/wallet/Wallet_BookERPMSA", "ADMIN", "Madwolves9810!");



				conn = DriverManager.getConnection("jdbc:oracle:thin:@bookerpmsa_high?TNS_ADMIN=C:/BookERP/src/main/java/com/bookstore/wallet/Wallet_BookERPMSA", "ADMIN", "Madwolves9810!");
>>>>>>> 1dfa6bbc17224b955e85a46774d317d7b18704ab
				System.out.println("connection sucess!!");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			return conn;
		}
	}
}