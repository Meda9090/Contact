package com.example.ddd.repository;

import com.example.ddd.entity.ContactMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ContactMongoRepository extends MongoRepository<ContactMongo, String> {
    Optional<ContactMongo> findByPhoneNumber(String phoneNumber);

}
