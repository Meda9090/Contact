package com.example.ddd.service;

import com.example.ddd.DTO.ContactMongoDTO;
import com.example.ddd.entity.ContactMongo;
import com.example.ddd.filter.Filter;
import com.example.ddd.repository.ContactMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ContactMongoService {

    @Autowired
    private ContactMongoRepository contactMongoRepository;

    public ContactMongo createContact(ContactMongoDTO contactMongoDTO) {
        ContactMongo contact = new ContactMongo();

        contact.setCreationDate(LocalDateTime.now());
        return contactMongoRepository.save(contact);
    }

    public Optional<ContactMongo> getContactById(String id) {
        return contactMongoRepository.findById(id);
    }

    public Optional<ContactMongo> getContactByPhoneNumber(String phoneNumber) {
        return contactMongoRepository.findByPhoneNumber(phoneNumber);
    }

    public void deleteContactById(String id) {
        contactMongoRepository.deleteById(id);
    }

    public void deleteContactByPhoneNumber(String phoneNumber) {
        contactMongoRepository.findByPhoneNumber(phoneNumber).ifPresent(contact -> contactMongoRepository.delete(contact));
    }

    public List<ContactMongo> getAllContacts(Filter filter) {
        Pageable pageable = PageRequest.of(filter.getOffset(), filter.getLimit());
        return contactMongoRepository.findAll(pageable).getContent();
    }

    public ContactMongo updateContactById(String id, ContactMongoDTO contactMongoDTO) {
        Optional<ContactMongo> existingContact = contactMongoRepository.findById(id);
        if (existingContact.isPresent()) {

            return contactMongoRepository.save(existingContact.get());
        } else {
            throw new RuntimeException("Контакт не найден");
        }
    }

    public ContactMongo updateContactByPhoneNumber(String phoneNumber, ContactMongoDTO contactMongoDTO) {
        Optional<ContactMongo> existingContact = contactMongoRepository.findByPhoneNumber(phoneNumber);
        if (existingContact.isPresent()) {
            // Set fields from contactMongoDTO
            return contactMongoRepository.save(existingContact.get());
        } else {
            throw new RuntimeException("Контакт не найден");
        }
    }
}
