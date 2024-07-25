package main.java.com.bookstore.dao;

import main.java.com.bookstore.model.User;
import main.java.com.bookstore.util.ConnectionHelper;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements Crud<User> {
    private Connection conn;

    public UserDAO() {
        conn = ConnectionHelper.getConnection();
    }

    @Override
    public void create(User user) {
        String sql = "INSERT INTO user (account, pw, phone) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getAccount());
            pstmt.setString(2, user.getPw());
            pstmt.setString(3, user.getPhone());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User read(String userId) {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getString("user_id"));
                user.setAccount(rs.getString("account"));
                user.setPw(rs.getString("pw"));
                user.setPhone(rs.getString("phone"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE user SET account = ?, pw = ?, phone = ? WHERE user_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getAccount());
            pstmt.setString(2, user.getPw());
            pstmt.setString(3, user.getPhone());
            pstmt.setString(4, user.getUserId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String userId) {
        String sql = "DELETE FROM user WHERE user_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> readAll() {
        String sql = "SELECT * FROM user";
        List<User> users = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getString("user_id"));
                user.setAccount(rs.getString("account"));
                user.setPw(rs.getString("pw"));
                user.setPhone(rs.getString("phone"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User readByAccount(String account) {
        String sql = "SELECT * FROM user WHERE account = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, account);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getString("user_id"));
                user.setAccount(rs.getString("account"));
                user.setPw(rs.getString("pw"));
                user.setPhone(rs.getString("phone"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}