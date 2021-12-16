package com.codecool.PawPrint.model.entity;

import com.codecool.PawPrint.model.service.Service;
import com.codecool.PawPrint.model.contact.Contact;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class User {

    private int id;
    private String fullName;
    private String userName;
    private LocalDateTime registrationTime;
    private String description;
    private Gender gender;
    private double age;
    private String email;
    private String password;
    private Contact contact;
    private UserType type;
    private Set<Pet> pets;
    private Set<User> friends;
    private Set<Service> services;
    private Set<Search> savedSearches;

    public User(String userName, LocalDateTime registrationTime, String email, String password, UserType type) {
        this.userName = userName;
        this.registrationTime = registrationTime;
        this.email = email;
        this.password = password;
        this.type = type;
    }
}
