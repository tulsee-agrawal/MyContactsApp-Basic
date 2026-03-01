package com.auth;
import com.user.UserController;
import com.user.User;

public class BasicAuth implements Authentication {
    @Override
    public User authenticate(String email, String password) {
        return UserController.getInstance().login(email, password); // Polymorphism
    }
}