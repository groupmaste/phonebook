package com.mycompany.phonebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Phonebook {
    // ArrayList to hold contacts (using HashMap for each contact's details)
    private ArrayList<HashMap<String, String>> contacts;

    public Phonebook() {
        contacts = new ArrayList<>();
    }

    // 1. Insert Contact
    public void insertContact(String name, String phone) {
        HashMap<String, String> contact = new HashMap<>();
        contact.put("name", name);
        contact.put("phone", phone);
        contacts.add(contact);
    }

    // 2. Search Contact
    public HashMap<String, String> searchContact(String name) {
        for (HashMap<String, String> contact : contacts) {
            if (contact.get("name").equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
    }

    // 3. Display All Contacts
    public void displayAllContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
            return;
        }
        for (HashMap<String, String> contact : contacts) {
            System.out.println("Name: " + contact.get("name") + ", Phone: " + contact.get("phone"));
        }
    }

    // 4. Delete Contact
    public boolean deleteContact(String name) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).get("name").equalsIgnoreCase(name)) {
                contacts.remove(i);
                return true;
            }
        }
        return false;
    }

    // 5. Update Contact
    public boolean updateContact(String name, String newName, String newPhone) {
        for (HashMap<String, String> contact : contacts) {
            if (contact.get("name").equalsIgnoreCase(name)) {
                contact.put("name", newName);
                contact.put("phone", newPhone);
                return true;
            }
        }
        return false;
    }

    // 6. Sort Contacts (Bubble Sort by Name)
    public void sortContacts() {
        int n = contacts.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                String name1 = contacts.get(j).get("name");
                String name2 = contacts.get(j + 1).get("name");
                if (name1.compareToIgnoreCase(name2) > 0) {
                    // Swap contacts
                    HashMap<String, String> temp = contacts.get(j);
                    contacts.set(j, contacts.get(j + 1));
                    contacts.set(j + 1, temp);
                }
            }
        }
    }

    // 7. Analyze the Efficiency of the Search Algorithm
    // This method provides information about the search algorithm's efficiency
    public void analyzeSearchEfficiency() {
        System.out.println("The search algorithm used is linear search.");
        System.out.println("Time Complexity: O(n) in the worst case, where n is the number of contacts.");
        System.out.println("Best Case: O(1) if the contact is the first element in the list.");
    }

    // Main method to interact with the Phonebook
    public static void main(String[] args) {
        Phonebook phonebook = new Phonebook();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nPhonebook Application");
            System.out.println("1. Insert Contact");
            System.out.println("2. Search Contact");
            System.out.println("3. Display All Contacts");
            System.out.println("4. Delete Contact");
            System.out.println("5. Update Contact");
            System.out.println("6. Sort Contacts");
            System.out.println("7. Analyze Search Efficiency");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phone = scanner.nextLine();
                    phonebook.insertContact(name, phone);
                    System.out.println("Contact inserted.");
                    break;
                case 2:
                    System.out.print("Enter name to search: ");
                    name = scanner.nextLine();
                    HashMap<String, String> foundContact = phonebook.searchContact(name);
                    if (foundContact != null) {
                        System.out.println("Contact found: Name: " + foundContact.get("name") + ", Phone: " + foundContact.get("phone"));
                    } else {
                        System.out.println("Contact not found in contacts list.");
                    }
                    break;
                case 3:
                    phonebook.displayAllContacts();
                    break;
                case 4:
                    System.out.print("Enter name to delete: ");
                    name = scanner.nextLine();
                    boolean deleted = phonebook.deleteContact(name);
                    if (deleted) {
                        System.out.println("Contact deleted.");
                    } else {
                        System.out.println("Contact not found in contacts.");
                    }
                    break;
                case 5:
                    System.out.print("Enter current name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new phone number: ");
                    String newPhone = scanner.nextLine();
                    boolean updated = phonebook.updateContact(name, newName, newPhone);
                    if (updated) {
                        System.out.println("Contact updated.");
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;
                case 6:
                    phonebook.sortContacts();
                    System.out.println("Contacts sorted.");
                    break;
                case 7:
                    phonebook.analyzeSearchEfficiency();
                    break;
                case 8:
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 8);
        scanner.close();
    }
}
