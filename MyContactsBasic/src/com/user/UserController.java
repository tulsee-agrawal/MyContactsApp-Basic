package com.user;

import com.contacts.*;
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
    public String updateProfile(User user, String newName, String newPhone) {
        if (newName.isEmpty() || !newPhone.matches("\\d{10}")) {
            return "Error: Invalid update data.";
        }
        user.setName(newName);
        user.setPhoneNumber(newPhone);
        return "Profile updated successfully!";
    }

    public String changePassword(User user, String oldPass, String newPass) {
        // Verify old password first
        if (!user.getHashPwd().equals(hashPassword(oldPass))) {
            return "Error: Old password does not match.";
        }
        if (newPass.length() < 6) {
            return "Error: New password too short.";
        }
        
        user.updatePassword(hashPassword(newPass));
        return "Password changed successfully!";
    }
    public void createContact(User currentUser, String name, String phone, String email, int type) {
        Contact newContact;
        if (type == 2) {
            newContact = new Organization(name); // Inheritance
        } else {
            newContact = new Person(name);
        }
        
        newContact.addPhone(phone); // Composition logic
        newContact.addEmail(email);
        
        currentUser.addContact(newContact);
    }
}

