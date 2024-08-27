package com.monetize360.cbook_server_app.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.monetize360.cbook_server_app.dao.ContactRepository;
import com.monetize360.cbook_server_app.domain.Contact;
import com.monetize360.cbook_server_app.dto.ContactDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ContactServiceImpl implements ContactService {



    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ContactDto addContact(ContactDto contactDto) {
        Contact contact = objectMapper.convertValue(contactDto, Contact.class);
        Contact savedContact = contactRepository.save(contact);
        log.info("Added contact with ID: {}", savedContact.getId());
        return objectMapper.convertValue(savedContact, ContactDto.class);
    }

    @Override
    public ContactDto updateContact(ContactDto contactDto) {
        Contact contact = objectMapper.convertValue(contactDto, Contact.class);

        Optional<Contact> existingContactOptional = contactRepository.findById(contact.getId());
        if (existingContactOptional.isEmpty()) {
            throw new EntityNotFoundException("Contact not found with ID: " + contact.getId());
        }

        Contact existingContact = existingContactOptional.get();
        existingContact.setFirstName(contact.getFirstName());
        existingContact.setLastName(contact.getLastName());
        existingContact.setEmail(contact.getEmail());
        existingContact.setMobile(contact.getMobile());

        Contact updatedContact = contactRepository.save(existingContact);

        log.info("Updated contact with ID: {}", updatedContact.getId());

        return objectMapper.convertValue(updatedContact, ContactDto.class);

    }

    @Override
    public ContactDto getContactById(UUID uuid) {


        return contactRepository.findById(uuid)
                .map(contact -> objectMapper.convertValue(contact, ContactDto.class))
                .orElse(null);
    }

    @Override
    public void deleteContact(UUID id) {
        Optional<Contact> contactOptional = contactRepository.findById(id);
        if (contactOptional.isEmpty()) {
            throw new EntityNotFoundException("Contact not found with ID: " + id);
        }

        Contact contact = contactOptional.get();
        contact.setDeleted(true);

        contactRepository.save(contact);


        log.info("Deleted contact with ID: {}", id);
    }

    @Override
    public List<ContactDto> getAllContacts(String field, String direction, int page, int size) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(field).ascending()
                : Sort.by(field).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Contact> contactPage = contactRepository.findAll(pageable);

        log.info("Contact count size is: {}", contactPage.getTotalElements());

        return objectMapper.convertValue(contactPage.getContent(), new TypeReference<List<ContactDto>>() {});
    }

    @Override
    public List<ContactDto> searchContacts(String search) {
        List<Contact> contacts = contactRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrMobileContainingIgnoreCase(
                        search,search,search,search);


        log.info("Search result count size is: {}", contacts.size());
        return objectMapper.convertValue(contacts, new TypeReference<List<ContactDto>>() {});
    }
    public BufferedImage generateQRCode(UUID id) throws Exception {
        ContactDto contact = getContactById(id);
        if (contact == null) {
            throw new EntityNotFoundException("Contact not found with ID: " + id);
        }

        String data = "UUID:" + id +
                "\nFirst Name: " + contact.getFirstName() +
                "\nLast Name: " + contact.getLastName() +
                "\nEmail: " + contact.getEmail() +
                "\nMobile: " + contact.getMobile();

        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = barcodeWriter.encode(data, BarcodeFormat.QR_CODE, 200, 200);

        log.info("Generated QR code for contact with ID: {}", id);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

}