package com.monetize360;

import com.monetize360.contactapp.domain.Contact;
import com.monetize360.contactapp.dto.ContactDto;
import com.monetize360.contactapp.service.ContactService;
import com.monetize360.contactapp.service.ContactServiceInterface;
import com.monetize360.contactapp.util.ConnectionUtil;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

    public class Main {
        public static void main(String[] args) {
            ContactServiceInterface contactService = new ContactService();
            Connection connection= ConnectionUtil.openConnection();
            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    System.out.println("1. Create Contact");
                    System.out.println("2. Update Contact");
                    System.out.println("3. Search Contacts");
                    System.out.println("4. Delete Contact");
                    System.out.println("5. Get Contact by ID");
                    System.out.println("6. Import to DB");
                    System.out.println("7. Export to Xlsx");
                    System.out.println("8. Exit");
                    System.out.print("Enter your choice: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    switch (choice) {
                        case 1:
                            // Create Contact
                            System.out.print("Enter name: ");
                            String name = scanner.nextLine();
                            System.out.print("Enter email: ");
                            String email = scanner.nextLine();
                            System.out.print("Enter date of birth (YYYY-MM-DD): ");
                            String dob = scanner.nextLine();
                            System.out.print("Enter mobile: ");
                            String mobile = scanner.nextLine();
                            ContactDto newContact = new ContactDto();
                            newContact.setName(name);
                            newContact.setEmail(email);
                            newContact.setDob(dob);
                            newContact.setMobile(mobile);
                            contactService.createContact(newContact,connection);
                            System.out.println("Contact created successfully.");
                            break;

                        case 2:
                            // Update Contact
                            System.out.print("Enter ID of contact to update: ");
                            int updateId = scanner.nextInt();
                            scanner.nextLine();  // Consume newline

                            System.out.println("Choose field to update:");
                            System.out.println("1. Name");
                            System.out.println("2. Email");
                            System.out.println("3. Date of Birth");
                            System.out.println("4. Mobile");
                            System.out.print("Enter your choice: ");
                            int fieldChoice = scanner.nextInt();
                            scanner.nextLine();  // Consume newline

                            ContactDto updateContact = contactService.getContactById(updateId,connection);
                            if (updateContact == null) {
                                System.out.println("Contact not found.");
                                break;
                            }

                            switch (fieldChoice) {
                                case 1:
                                    System.out.print("Enter new name: ");
                                    String newName = scanner.nextLine();
                                    updateContact.setName(newName);
                                    break;
                                case 2:
                                    System.out.print("Enter new email: ");
                                    String newEmail = scanner.nextLine();
                                    updateContact.setEmail(newEmail);
                                    break;
                                case 3:
                                    System.out.print("Enter new date of birth (YYYY-MM-DD): ");
                                    String newDob = scanner.nextLine();
                                    updateContact.setDob(newDob);
                                    break;
                                case 4:
                                    System.out.print("Enter new mobile: ");
                                    String newMobile = scanner.nextLine();
                                    updateContact.setMobile(newMobile);
                                    break;
                                default:
                                    System.out.println("Invalid choice. No changes made.");
                                    continue;
                            }

                            contactService.updateContact(updateId, updateContact,connection);
                            System.out.println("Contact updated successfully.");
                            break;

                        case 3:
                            // Search Contacts
                            System.out.print("Enter search query (name/email): ");
                            String query = scanner.nextLine();
                            List<ContactDto> contacts = contactService.searchContacts(query,connection);
                            System.out.println("Search results:");
                            for (ContactDto contact : contacts) {
                                System.out.println(contact);
                            }
                            break;

                        case 4:
                            // Delete Contact
                            System.out.print("Enter ID of contact to delete: ");
                            int deleteId = scanner.nextInt();
                            contactService.deleteContact(deleteId,connection);
                            System.out.println("Contact deleted successfully.");
                            break;

                        case 5:
                            // Get Contact by ID
                            System.out.print("Enter ID of contact to retrieve: ");
                            int getId = scanner.nextInt();
                            ContactDto contact = contactService.getContactById(getId,connection);
                            if (contact != null) {
                                System.out.println("Contact details:");
                                System.out.println(contact);
                            } else {
                                System.out.println("Contact not found.");
                            }
                            break;
                        case 6:
                            System.out.println("Importing contacts from Xlsx....");
                            contactService.importContactsFromExcel(connection);
                            break;
                        case 7:
                            System.out.println("Exporting contacts to DB....");
                            try (OutputStream outputStream = new FileOutputStream("C:\\Users\\VikasPai\\Desktop\\Training\\mtz-java-training\\contact\\src\\main\\resources\\contact.xlsx")) {
                                boolean exported = contactService.exportContactsToExcel(outputStream,connection);
                                if (exported) {
                                    System.out.println("Contacts exported successfully.");
                                } else {
                                    System.out.println("Failed to export contacts.");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case 8:
                            // Exit
                            System.out.println("Exiting...");
                            return;

                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                }
            }
            finally {
                ConnectionUtil.closeConn(connection);
            }
        }
    }