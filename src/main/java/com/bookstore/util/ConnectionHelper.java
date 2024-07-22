package main.java.com.bookstore.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
    private static Connection connection;

    private ConnectionHelper() {
        // Private constructor to prevent instantiation
    }

    public static Connection getConnection() {
        if (connection == null) {
            synchronized (ConnectionHelper.class) {
                if (connection == null) {
                    try {
                        String url = "jdbc:oracle:thin:@bookerpmsa_high?TNS_ADMIN=C:/Users/user/git/BookERP/src/main/java/com/bookstore/wallet/Wallet_BookERPMSA";
                        String user = "ADMIN";
                        String password = "Madwolves9810!";
                        connection = DriverManager.getConnection(url, user, password);
                        System.out.println("연결완료!");
          
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
