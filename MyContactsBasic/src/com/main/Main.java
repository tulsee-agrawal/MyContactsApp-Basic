/**
 * User Contact Management
 * User can view Contacts
 *
 * @author Tulsee Agrawal
 * @version 5.0
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
        User sessionUser = null; 

        while (true) {
            if (sessionUser == null) {
                System.out.println("\n--- CONTACTS APP ---");
                System.out.println("1. Register\n2. Login\n3. Exit");
                System.out.print("Choice: ");
                String choice = sc.nextLine();

                if (choice.equals("1")) {
                    System.out.print("Name: "); String n = sc.nextLine();
                    System.out.print("Email: "); String e = sc.nextLine();
                    System.out.print("Pass: "); String p = sc.nextLine();
                    System.out.print("Phone: "); String ph = sc.nextLine();
                    System.out.println(userCtrl.register(n, e, p, ph, UserType.FREE));

                } else if (choice.equals("2")) {
                    // Login Logic (UC-02)
                    System.out.print("Email: "); String e = sc.nextLine();
                    System.out.print("Password: "); String p = sc.nextLine();
                    sessionUser = auth.authenticate(e, p); // Now updates the top-level variable
                    
                    if (sessionUser == null) System.out.println("Login Failed.");

                } else if (choice.equals("3")) break;

            } else {
                System.out.println("\n--- WELCOME " + sessionUser.getName() + " ---");
                System.out.println("1. Update Profile");
                System.out.println("2. Create Contact");
                System.out.println("3. List All Contacts");
                System.out.println("4. View Contact Details");
                System.out.println("5. Logout");
                System.out.print("Choice: ");
                String choice = sc.nextLine();

                if (choice.equals("1")) {
                    // UC-03 Logic
                    System.out.print("New Name: "); String nName = sc.nextLine();
                    System.out.print("New Phone: "); String nPhone = sc.nextLine();
                    System.out.println(userCtrl.updateProfile(sessionUser, nName, nPhone));
                } else if(choice.equals("2")) {
                	System.out.print("Contact Name: ");
                    String cName = sc.nextLine();
                    System.out.print("Phone Number: ");
                    String cPhone = sc.nextLine();
                    System.out.print("Email: ");
                    String cEmail = sc.nextLine();
                    System.out.print("Type (1: Person, 2: Organization): ");
                    int cType = Integer.parseInt(sc.nextLine());

                    userCtrl.createContact(sessionUser, cName, cPhone, cEmail, cType);
                    System.out.println("Contact added to your list!");
                	
                	
                } else if (choice.equals("3")) {
                    userCtrl.listContacts(sessionUser);

                } else if (choice.equals("4")) {
                    System.out.print("Enter Contact ID to view: ");
                    String idToView = sc.nextLine();
                    userCtrl.viewContactDetails(sessionUser, idToView);
                }else if (choice.equals("5")) {
                    sessionUser = null; // Logout
                    System.out.println("Logged out.");
                }
            }
        }
        sc.close();
    }
}