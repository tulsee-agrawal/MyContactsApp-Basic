package com.user;

public class User {
    private String name;
    private String email;
    private String hashedPassword;
    private String phoneNumber;
    private UserType type;

    public User(String name, String email, String hashedPassword, String phoneNumber, UserType type) {
        this.name = name;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.phoneNumber = phoneNumber;
        this.type = type;
    }

    // Getters for Encapsulation
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getHashPwd() { return hashedPassword; } 
    public String getPhoneNumber() { return phoneNumber; }
    public UserType getType() { return type; }
}
