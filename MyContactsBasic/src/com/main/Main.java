/**
 * User Authentication
 * It checks whether the user is registered and does user authentication
 *
 * @author Tulsee Agrawal
 * @version 2.0
 */

package com.main;

import com.user.UserController;
import com.user.UserType;
import com.user.User;
import com.auth.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserController userCtrl = UserController.getInstance();
        Authentication auth = new BasicAuth();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- CONTACTS APP MENU ---");
            System.out.println("1. Register\n2. Login\n3. Exit");
            System.out.print("Choice: ");
            String choice = sc.nextLine();

            if (choice.equals("1")) {
                System.out.print("Enter Name: ");
                String name = sc.nextLine();

                String email = "";
                while (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                    System.out.print("Enter Email (valid format): ");
                    email = sc.nextLine();
                }

                String pwd = "";
                while (pwd.length() < 6) {
                    System.out.print("Enter Password (min 6 chars): ");
                    pwd = sc.nextLine();
                }

                String ph = "";
                while (!ph.matches("\\d{10}")) {
                    System.out.print("Enter Phone (10 digits only): ");
                    ph = sc.nextLine();
                }

                System.out.println(userCtrl.register(name, email, pwd, ph, UserType.FREE));

            } else if (choice.equals("2")) {
                System.out.print("Email: "); String e = sc.nextLine();
                System.out.print("Password: "); String p = sc.nextLine();
                User u = auth.authenticate(e, p);
                if (u != null) {
                    System.out.println("Login Success! Welcome " + u.getName());
                    // Proceed to UC-04...
                    break;
                } else System.out.println("Invalid credentials.");
            } else if (choice.equals("3")) break;
        }
        sc.close();
    }
}