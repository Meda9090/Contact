package com.example.ddd.service;

import com.example.ddd.filter.Filter;
import com.example.ddd.entity.Contact;
import com.example.ddd.DTO.ContactDTO;
import com.example.ddd.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Contact createdContact(ContactDTO contactDTO) {
        Contact contact = new Contact();

        contact.setCreationDate(LocalDateTime.now());
        return contactRepository.save(contact);
    }


    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }

    public Optional<Contact> getContactByPhoneNumber(String phoneNumber) {
        return contactRepository.findByPhoneNumber(phoneNumber);
    }

    public void deleteContactById(Long id) {
        contactRepository.deleteById(id);
    }

    public void deleteContactByPhoneNumber(String phoneNumber) {
        contactRepository.findByPhoneNumber(phoneNumber).ifPresent(contact -> contactRepository.delete(contact));
    }

    public List<Contact> getAllContacts(Filter filter) {
        Pageable pageable = PageRequest.of(filter.getOffset(), filter.getLimit());
        return contactRepository.findAll(pageable).getContent();
    }

    public Contact updateContactById(Long id, ContactDTO contactDTO) {
        Optional<Contact> existingContact = contactRepository.findById(id);
        if (existingContact.isPresent()) {

            return contactRepository.save(existingContact.get());
        } else {
            throw new RuntimeException("Контакт не найден");
        }
    }

    public Contact updateContactByPhoneNumber(String phoneNumber, ContactDTO contactDTO) {
        Optional<Contact> existingContact = contactRepository.findByPhoneNumber(phoneNumber);
        if (existingContact.isPresent()) {

            return contactRepository.save(existingContact.get());
        } else {
            throw new RuntimeException("Контакт не найден");
        }
    }
}
