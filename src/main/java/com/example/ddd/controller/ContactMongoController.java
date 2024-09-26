package com.example.ddd.controller;

import com.example.ddd.DTO.ContactMongoDTO;
import com.example.ddd.entity.ContactMongo;
import com.example.ddd.filter.Filter;
import com.example.ddd.service.ContactMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mongo-contacts")
public class ContactMongoController {

    @Autowired
    private ContactMongoService contactMongoService;

    @PostMapping
    public ResponseEntity<ContactMongo> createContact(@RequestBody ContactMongoDTO contactMongoDTO) {
        ContactMongo createdContact = contactMongoService.createContact(contactMongoDTO);
        return new ResponseEntity<>(createdContact, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactMongo> getContactById(@PathVariable String id) {
        Optional<ContactMongo> contact = contactMongoService.getContactById(id);
        if (contact.isPresent()) {
            return new ResponseEntity<>(contact.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<ContactMongo> getContactByPhoneNumber(@PathVariable String phoneNumber) {
        Optional<ContactMongo> contact = contactMongoService.getContactByPhoneNumber(phoneNumber);
        if (contact.isPresent()) {
            return new ResponseEntity<>(contact.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContactById(@PathVariable String id) {
        contactMongoService.deleteContactById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/phone/{phoneNumber}")
    public ResponseEntity<Void> deleteContactByPhoneNumber(@PathVariable String phoneNumber) {
        contactMongoService.deleteContactByPhoneNumber(phoneNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<ContactMongo>> getAllContacts(@RequestParam(required = false) Integer limit,
                                                             @RequestParam(required = false) Integer offset) {
        Filter filter = new Filter(limit, offset);
        List<ContactMongo> contacts = contactMongoService.getAllContacts(filter);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactMongo> updateContactById(@PathVariable String id, @RequestBody ContactMongoDTO contactMongoDTO) {
        Optional<ContactMongo> existingContact = contactMongoService.getContactById(id);
        if (existingContact.isPresent()) {
            // Set fields from contactMongoDTO
            return new ResponseEntity<>(contactMongoService.updateContactById(id, contactMongoDTO), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/phone/{phoneNumber}")
    public ResponseEntity<ContactMongo> updateContactByPhoneNumber(@PathVariable String phoneNumber, @RequestBody ContactMongoDTO contactMongoDTO) {
        Optional<ContactMongo> existingContact = contactMongoService.getContactByPhoneNumber(phoneNumber);
        if (existingContact.isPresent()) {
            // Set fields from contactMongoDTO
            return new ResponseEntity<>(contactMongoService.updateContactByPhoneNumber(phoneNumber, contactMongoDTO), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
