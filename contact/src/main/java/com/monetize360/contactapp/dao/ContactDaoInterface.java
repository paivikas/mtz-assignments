package com.monetize360.contactapp.dao;

import com.monetize360.contactapp.dto.ContactDto;

import java.sql.Connection;
import java.util.List;

public interface ContactDaoInterface {
    void createContact(ContactDto contact, Connection connection);

    // Update an existing contact
    void updateContact(int id, ContactDto contact,Connection connection);

    // Search for contacts by name, email, etc.
    List<ContactDto> searchContacts(String query, Connection connection);

    // Delete a contact by ID
    void deleteContact(int id,Connection connection);

    // Get a contact by ID
    ContactDto getContactById(int id,Connection connection);
    List<ContactDto> getAllContacts(Connection conn);
}
