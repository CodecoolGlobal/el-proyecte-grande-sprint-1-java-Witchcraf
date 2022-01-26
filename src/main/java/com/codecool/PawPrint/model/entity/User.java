package com.codecool.PawPrint.model.entity;

import com.codecool.PawPrint.model.service.ServiceOffered;
import com.codecool.PawPrint.model.contact.Contact;
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
@Table(name = "user_")
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )

    private int id;
    private String fullName;
    private String username;
    private LocalDateTime registrationTime;
    private String description;
    private Gender gender;
    private double age;
    private String email;
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    private Contact contact;
    private UserType userType;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Pet> pets = new HashSet<>();
//    private Set<User> friends;        // how to annotate self-aggregation?
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)  // mapped by user removed
    private Set<ServiceOffered> services = new HashSet<>();
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Search> savedSearches = new HashSet<>();

    public User(String userName, LocalDateTime registrationTime, String email, int age, String password, UserType userType) {
        this.username = userName;
        this.registrationTime = registrationTime;
        this.email = email;
        this.age = age;
        this.password = password;
        this.userType = userType;
    }
}
