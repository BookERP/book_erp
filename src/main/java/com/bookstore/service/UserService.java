package main.java.com.bookstore.service;

import main.java.com.bookstore.dao.UserDAO;
import main.java.com.bookstore.model.User;

public class UserService {
    private UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    // 회원 가입
    public void registerUser(User user) {
        // 사용자 정보 유효성 검사
        if (user.getAccount().isEmpty() || user.getPw().isEmpty()) {
            throw new IllegalArgumentException("사용자 정보가 유효하지 않습니다.");
        }
        // 비밀번호 일치 여부 검사
        if (!user.getPw().equals(user.getCpw())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        userDAO.create(user);
    }

    // 로그인
    public boolean login(String account, String password) {
        User user = userDAO.readByAccount(account);
        if (user != null && user.getPw().equals(password)) {
            return true;
        }
        return false;
    }

    // 사용자 정보 수정
    public void updateUser(User user) {
        // 사용자 정보 유효성 검사
        if (user.getUserId().isEmpty() || user.getAccount().isEmpty()) {
            throw new IllegalArgumentException("사용자 정보가 유효하지 않습니다.");
        }
        userDAO.update(user);
    }

    // 사용자 탈퇴
    public void deleteUser(String userId) {
        userDAO.delete(userId);
    }

    // 사용자 검색
    public User searchUser(String userId) {
        return userDAO.read(userId);
    }
}