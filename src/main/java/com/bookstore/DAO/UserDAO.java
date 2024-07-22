package main.java.com.bookstore.DAO;

import main.java.com.bookstore.model.User;
import main.java.com.bookstore.model.UserRole;
import main.java.com.bookstore.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DatabaseOperations<User> {
    @Override
    public void insert(User user) throws SQLException {
        String sql = "INSERT INTO Users (UserId, Username, Password, Email, Role, RegistrationDate) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getRole().name());
            pstmt.setTimestamp(6, Timestamp.valueOf(user.getRegistrationDate()));
            pstmt.executeUpdate();
        }
    }

    @Override
    public User getById(String id) throws SQLException {
        String sql = "SELECT * FROM Users WHERE UserId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractUserFromResultSet(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                users.add(extractUserFromResultSet(rs));
            }
        }
        return users;
    }

    @Override
    public void update(User user) throws SQLException {
        String sql = "UPDATE Users SET Username = ?, Password = ?, Email = ?, Role = ? WHERE UserId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getRole().name());
            pstmt.setString(5, user.getUserId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM Users WHERE UserId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }

    public User getByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM Users WHERE Username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractUserFromResultSet(rs);
                }
            }
        }
        return null;
    }

    private User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getString("UserId"));
        user.setUsername(rs.getString("Username"));
        user.setPassword(rs.getString("Password"));
        user.setEmail(rs.getString("Email"));
        user.setRole(UserRole.valueOf(rs.getString("Role")));
        user.setRegistrationDate(rs.getTimestamp("RegistrationDate").toLocalDateTime());
        return user;
    }
}
