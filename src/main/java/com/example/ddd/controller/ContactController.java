package com.example.ddd.controller;


import com.example.ddd.DTO.ContactDTO;
import com.example.ddd.service.ContactService;
import com.example.ddd.filter.Filter;
import com.example.ddd.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody ContactDTO contactDTO) {
        Contact createdContact = contactService.createdContact(contactDTO);
        return new ResponseEntity<>(createdContact, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        Optional<Contact> contact = contactService.getContactById(id);
        if (contact.isPresent()) {
            return new ResponseEntity<>(contact.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<Contact> getContactByPhoneNumber(@PathVariable String phoneNumber) {
        Optional<Contact> contact = contactService.getContactByPhoneNumber(phoneNumber);
        if (contact.isPresent()) {
            return new ResponseEntity<>(contact.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContactById(@PathVariable Long id) {
        contactService.deleteContactById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/phone/{phoneNumber}")
    public ResponseEntity<Void> deleteContactByPhoneNumber(@PathVariable String phoneNumber) {
        contactService.deleteContactByPhoneNumber(phoneNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts(@RequestParam(required = false) Integer limit,
                                                        @RequestParam(required = false) Integer offset) {
        Filter filter = new Filter(limit, offset);
        List<Contact> contacts = contactService.getAllContacts(filter);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContactById(@PathVariable Long id, @RequestBody ContactDTO contactDTO) {
        Optional<Contact> existingContact = contactService.getContactById(id);
        if (existingContact.isPresent()) {
            // Set fields from contactDTO
            return new ResponseEntity<>(contactService.updateContactById(id, contactDTO), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/phone/{phoneNumber}")
    public ResponseEntity<Contact> updateContactByPhoneNumber(@PathVariable String phoneNumber, @RequestBody ContactDTO contactDTO) {
        Optional<Contact> existingContact = contactService.getContactByPhoneNumber(phoneNumber);
        if (existingContact.isPresent()) {
            // Set fields from contactDTO
            return new ResponseEntity<>(contactService.updateContactByPhoneNumber(phoneNumber, contactDTO), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}