package com.codecool.PawPrint.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @SequenceGenerator(
            name = "pet_sequence",
            sequenceName = "pet_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pet_sequence"
    )

    private int id;
    @ManyToOne
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
//    private Set<Pet> petFriends = new HashSet<>();

    public Pet(String userName, LocalDateTime registrationTime, User user, PetType petType) {
        this.userName = userName;
        this.registrationTime = registrationTime;
        this.user = user;
        this.petType = petType;
    }
}
