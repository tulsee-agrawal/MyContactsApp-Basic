/**
 * User Contact Management
 * User can now search for contacts
 *
 * @author Tulsee Agrawal
 * @version 9.0
 */

package com.main;

import com.user.UserController;
import com.user.UserType;
import com.user.User;
import com.auth.*;
import com.search.*;
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
                System.out.println("5. Edit Contact");
                System.out.println("6. Delete Contact");
                System.out.println("7. Bulk Delete by Name");
                System.out.println("8. Search Contacts");
                System.out.println("9. Logout");
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
                    System.out.print("Enter Contact ID to edit: ");
                    String idToEdit = sc.nextLine();
                    
                    System.out.print("New Name: ");
                    String nName = sc.nextLine();
                    System.out.print("New Phone: ");
                    String nPhone = sc.nextLine();
                    System.out.print("New Email: ");
                    String nEmail = sc.nextLine();

                    String result = userCtrl.editContact(sessionUser, idToEdit, nName, nPhone, nEmail);
                    System.out.println(result);
                }else if (choice.equals("6")) {
                    System.out.print("Enter Contact ID to delete: ");
                    String idToDelete = sc.nextLine();
                    
                    // Confirmation Dialog
                    System.out.print("Are you sure you want to delete this contact? (y/n): ");
                    String confirm = sc.nextLine();
                    
                    if (confirm.equalsIgnoreCase("y")) {
                        boolean success = userCtrl.deleteContact(sessionUser, idToDelete);
                        if (success) {
                            System.out.println("Contact deleted successfully.");
                        } else {
                            System.out.println("Error: Contact ID not found.");
                        }
                    } else {
                        System.out.println("Deletion cancelled.");
                    }
                }else if (choice.equals("7")) {
                    java.util.List<String> targetNames = new java.util.ArrayList<>(); // List collection
                    
                    System.out.println("Enter names to delete (type 'done' to finish):");
                    while (true) {
                        System.out.print("> ");
                        String nameInput = sc.nextLine();
                        if (nameInput.equalsIgnoreCase("done")) break;
                        if (!nameInput.trim().isEmpty()) {
                            targetNames.add(nameInput);
                        }
                    }

                    if (!targetNames.isEmpty()) {
                        System.out.print("Are you sure you want to delete these " + targetNames.size() + " names? (y/n): ");
                        if (sc.nextLine().equalsIgnoreCase("y")) {
                            int deleted = userCtrl.bulkDeleteByNames(sessionUser, targetNames);
                            System.out.println("SUCCESS: " + deleted + " contacts removed from your list.");
                        }
                    } else {
                        System.out.println("No names entered.");
                    }
                } else if (choice.equals("8")) {
                    System.out.println("Search by: 1. Name | 2. Phone");
                    String type = sc.nextLine();
                    System.out.print("Enter search term: ");
                    String query = sc.nextLine();

                    SearchCriteria criteria = type.equals("2") ? new PhoneSearch() : new NameSearch();
                    userCtrl.performSearch(sessionUser, criteria, query);
                }
                else if (choice.equals("9")) {
                    sessionUser = null; // Logout
                    System.out.println("Logged out.");
                }
            }
        }
        sc.close();
    }
}