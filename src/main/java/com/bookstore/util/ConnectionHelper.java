package main.java.com.bookstore.util;

import java.sql.*;

public class ConnectionHelper {
    private static Connection connection;

    public ConnectionHelper() {
        // Private constructor to prevent instantiation
    }

    public static Connection getConnection() {
        if (connection == null) {
            synchronized (ConnectionHelper.class) {
                if (connection == null) {
                    try {
                        String url = "jdbc:oracle:thin:@bookerpmsa_high?TNS_ADMIN=D:/Wallet_BookERPMSA";
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

    protected void closeResources(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}