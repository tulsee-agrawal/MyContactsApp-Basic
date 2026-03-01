package com.main;

import com.user.UserController;
import com.user.UserType;
import com.user.User;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserController userCtrl = UserController.getInstance();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== UC-01: User Registration ===");

        // --- Validated Input ---
        String name = "";
        while (name.isEmpty()) {
            System.out.print("Enter Name: ");
            name = scanner.nextLine().trim();
        }

        String email = "";
        while (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            System.out.print("Enter Email: ");
            email = scanner.nextLine().trim();
        }

        String password = "";
        while (password.length() < 6) {
            System.out.print("Enter Password (min 6 chars): ");
            password = scanner.nextLine();
        }

        String phone = "";
        while (!phone.matches("\\d{10}")) {
            System.out.print("Enter Phone (10 digits): ");
            phone = scanner.nextLine().trim();
        }

        UserType type = null;
        while (type == null) {
            System.out.print("Enter User Type (1: FREE, 2: PREMIUM): ");
            String choice = scanner.nextLine();
            if (choice.equals("1")) type = UserType.FREE;
            else if (choice.equals("2")) type = UserType.PREMIUM;
        }

        // --- Process ---
        String result = userCtrl.register(name, email, password, phone, type);
        System.out.println("\nResult: " + result);

        // --- Verification ---
        User savedUser = userCtrl.getUserDatabase().get(email.toLowerCase());
        if (savedUser != null) {
            System.out.println("\n--- Verification ---");
            System.out.println("Email: " + savedUser.getEmail());
            System.out.println("Hash: " + savedUser.getHashPwd());
            System.out.println("Type: " + savedUser.getType());
        }
        
        scanner.close();
    }
}