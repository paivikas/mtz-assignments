package com.monetize360.contact_web_using_jwt.service;

import com.monetize360.contact_web_using_jwt.dto.ContactDto;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.UUID;

public interface ContactService {
    ContactDto addContact(ContactDto contactDto);
    ContactDto updateContact(ContactDto contactDto);
    void deleteContact(UUID uuid);
    ContactDto getContactById(UUID uuid);
    List<ContactDto> getAllContacts(String field, String direction, int page, int size);
    BufferedImage generateQRCode(UUID id) throws Exception;
    void importContacts(MultipartFile file);
    byte[] exportContacts();
    void importContactsFromCSV(MultipartFile file);
}
