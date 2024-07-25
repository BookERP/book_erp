package main.java.com.bookstore.controller;

import main.java.com.bookstore.model.User;
import main.java.com.bookstore.service.UserService;

public class UserController {
    private UserService userService;

    public UserController() {
        userService = new UserService();
    }

    // 회원 가입
    public void registerUser(User user) {
        userService.registerUser(user);
    }

    // 로그인
    public boolean login(String account, String password) {
        return userService.login(account, password);
    }

    // 사용자 정보 수정
    public void updateUser(User user) {
        userService.updateUser(user);
    }

    // 사용자 탈퇴
    public void deleteUser(String userId) {
        userService.deleteUser(userId);
    }

    // 사용자 검색
    public User searchUser(String userId) {
        return userService.searchUser(userId);
    }
}