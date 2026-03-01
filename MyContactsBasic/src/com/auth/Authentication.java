package com.auth;


import com.user.User;

public interface Authentication {
    User authenticate(String email, String password); // Abstraction
}
