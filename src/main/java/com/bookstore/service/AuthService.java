package main.java.com.bookstore.service;

import main.java.com.bookstore.DAO.UserDAO;
import main.java.com.bookstore.model.User;
import main.java.com.bookstore.model.UserRole;

import java.sql.SQLException;
import java.util.UUID;

public class AuthService {
    private UserDAO userDAO;

    public AuthService() {
        this.userDAO = new UserDAO();
    }

    public boolean register(String username, String password, String email) {
        User user = new User(
                UUID.randomUUID().toString(),
                username,
                password,  // 실제 애플리케이션에서는 비밀번호를 해시화해야 합니다.
                email,
                UserRole.CUSTOMER
        );

        try {
            userDAO.insert(user);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User login(String username, String password) {
        try {
            User user = userDAO.getByUsername(username);
            if (user != null && user.getPassword().equals(password)) {  // 실제 애플리케이션에서는 해시된 비밀번호를 비교해야 합니다.
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

