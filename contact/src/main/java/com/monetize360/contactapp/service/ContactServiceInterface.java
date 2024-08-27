package com.monetize360.contactapp.service;
import com.monetize360.contactapp.domain.Contact;
import com.monetize360.contactapp.dto.ContactDto;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.List;



    public interface ContactServiceInterface {
        // Create a new contact
        boolean createContact(ContactDto contact, Connection connection);

        // Update an existing contact
        boolean updateContact(int id, ContactDto contact,Connection connection);

        // Search for contacts by name, email, etc.
        List<ContactDto> searchContacts(String query,Connection connection);

        // Delete a contact by ID
        boolean deleteContact(int id,Connection connection);

        // Get a contact by ID
        ContactDto getContactById(int id,Connection connection);

        // Import contacts from an XLSX file
        boolean importContactsFromExcel(Connection connection);

        // Export contacts to an XLSX file
        boolean exportContactsToExcel(OutputStream outputStream, Connection connection);
        List<ContactDto> getAllContacts(Connection conn);
    }

