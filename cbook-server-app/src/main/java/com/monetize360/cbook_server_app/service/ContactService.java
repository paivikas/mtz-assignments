package com.monetize360.cbook_server_app.service;

import com.monetize360.cbook_server_app.domain.Contact;
import com.monetize360.cbook_server_app.dto.ContactDto;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.UUID;

public interface ContactService {
    ContactDto addContact(ContactDto contactDto);
    ContactDto updateContact(ContactDto contactDto);
    ContactDto getContactById(UUID uuid);
    void deleteContact(UUID uuid);
    List<ContactDto> getAllContacts(String field, String direction, int page, int size);
    List<ContactDto> searchContacts(String search);
    BufferedImage generateQRCode(UUID id) throws Exception;
}
