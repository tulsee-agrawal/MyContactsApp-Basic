package com.user;


import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public class UserController {
    private static UserController instance;
    private Map<String, User> userDatabase = new HashMap<>();

    private UserController() {}

    public static UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }
        return instance;
    }

    public Map<String, User> getUserDatabase() {
        return userDatabase;
    }

    public String register(String name, String email, String password, String phone, UserType type) {
        // Final logic-level validation
        if (userDatabase.containsKey(email.toLowerCase())) {
            return "Error: User already exists with this email.";
        }

        String hashed = hashPassword(password);
        User newUser = new User(name, email.toLowerCase(), hashed, phone, type);
        userDatabase.put(email.toLowerCase(), newUser);

        return "Success: Account created for " + name;
    }
    public User login(String email, String password) {
        User user = userDatabase.get(email.toLowerCase());
        if (user != null && user.getHashPwd().equals(hashPassword(password))) return user;
        return null;
    }
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            return password;
        }
    }
}

