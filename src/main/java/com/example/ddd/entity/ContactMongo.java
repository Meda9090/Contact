package com.example.ddd.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class ContactMongo {

    @Id
    private String id;

    private String name;
    private Integer birthYear;
    private String phoneNumber;
    private String secondaryPhoneNumber;
    private LocalDateTime creationDate;
}
