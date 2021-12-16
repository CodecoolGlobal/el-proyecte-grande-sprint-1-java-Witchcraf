package com.codecool.PawPrint.model.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Pet {

    private int id;
    private User user;
    private String fullName;
    private String userName;
    private LocalDateTime registrationTime;
    private String description;
    private Gender gender;
    private double age;
    private PetType petType;
    private String breed;
    private PetType compatibility;
    private Set<Pet> petFriends = new HashSet<>();

    public Pet(String userName, LocalDateTime registrationTime, User user, PetType petType) {
        this.userName = userName;
        this.registrationTime = registrationTime;
        this.user = user;
        this.petType = petType;
    }
}
