package com.monetize360.contactapp.dao;

import com.monetize360.contactapp.dto.ContactDto;
import org.modelmapper.ModelMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDaoImpl implements ContactDaoInterface{
    @Override
    public void createContact(ContactDto contact, Connection connection) {
        String sql = "INSERT INTO contacts (name, email, dob, mobile) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, contact.getName());
            stmt.setString(2, contact.getEmail());


            if (contact.getDob() != null && !contact.getDob().isEmpty()) {
                try {
                    stmt.setDate(3, Date.valueOf(contact.getDob()));
                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid date format for DOB: " + contact.getDob());
                    return;
                }
            } else {
                stmt.setNull(3, Types.DATE);
            }


            try {
                stmt.setLong(4, Long.parseLong(contact.getMobile()));
            } catch (NumberFormatException e) {
                System.err.println("Invalid mobile number: " + contact.getMobile());
                return;
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateContact(int id, ContactDto contact,Connection connection) {
        String sql = "UPDATE contacts SET name = ?, email = ?, dob = ?, mobile = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, contact.getName());
            stmt.setString(2, contact.getEmail());
            stmt.setDate(3, Date.valueOf(contact.getDob()));
            stmt.setLong(4, Long.parseLong(contact.getMobile()));
            stmt.setInt(5, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ContactDto> searchContacts(String query, Connection connection) {
        List<ContactDto> contacts = new ArrayList<>();
        String sql = "SELECT * FROM contacts WHERE name ILIKE ? OR email ILIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + query + "%");
            stmt.setString(2, "%" + query + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ContactDto contact = new ContactDto();
                contact.setId(rs.getInt("id"));
                contact.setName(rs.getString("name"));
                contact.setEmail(rs.getString("email"));
                contact.setDob(rs.getDate("dob").toString());
                contact.setMobile(rs.getLong("mobile") + "");
                contacts.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    @Override
    public void deleteContact(int id,Connection connection) {
        String sql = "DELETE FROM contacts WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ContactDto getContactById(int id,Connection connection) {
        ContactDto contact = null;
        String sql = "SELECT * FROM contacts WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                contact = new ContactDto();
                contact.setId(rs.getInt("id"));
                contact.setName(rs.getString("name"));
                contact.setEmail(rs.getString("email"));
                contact.setDob(rs.getDate("dob").toString());
                contact.setMobile(rs.getLong("mobile") + "");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contact;
    }
    @Override
    public List<ContactDto> getAllContacts(Connection conn) {
        String query = "SELECT * FROM contacts";
        List<ContactDto> contacts = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ModelMapper modelMapper=new ModelMapper();
                ContactDto contact = modelMapper.map(rs, ContactDto.class);
                contact.setName(rs.getString("name"));
                contact.setEmail(rs.getString("email"));
                contact.setDob(rs.getDate("dob").toString());
                contact.setMobile(rs.getString("mobile"));

                contacts.add(modelMapper.map(contact, ContactDto.class));
            }
        }
        catch (SQLException s){
            s.printStackTrace();
        }
        return contacts;
    }
}
