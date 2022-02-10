package com.codecool.PawPrint.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@JsonIgnoreProperties(value = {"user"})
@EqualsAndHashCode(exclude = {"user"})
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
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonProperty("user")
    @JsonIgnoreProperties("pets")
    @ToString.Exclude
    private User user;
    private String fullName;
    private String username;
    private LocalDateTime registrationTime;
    private String description;
    private Gender gender;
    private double age;
    private PetType petType;
    private String breed;
    private PetType compatibility;
//    private Set<Pet> petFriends = new HashSet<>();

    public Pet(String username, LocalDateTime registrationTime, User user, PetType petType) {
        this.username = username;
        this.registrationTime = registrationTime;
        this.user = user;
        this.petType = petType;
    }
}
