package com.codecool.PawPrint.model.entity;

import com.codecool.PawPrint.model.service.ServiceOffered;
import com.codecool.PawPrint.model.contact.Contact;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
//@JsonIgnoreProperties(value = {"services", "pets", "savedSearches"})
//@JsonIgnoreProperties(value = {"services", "pets"})
@JsonIgnoreProperties(value = {"pets"})
@EqualsAndHashCode(exclude = {"services", "pets", "savedSearches"})
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
    @ToString.Exclude
    private Contact contact;
    private UserType userType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonProperty("pets")
    @JsonIgnoreProperties("user")
    @ToString.Exclude
    private Set<Pet> pets = new HashSet<>();
//    private Set<User> friends;        // how to annotate self-aggregation?
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")  // mapped by user removed
//    @JsonProperty("services")       // prevents circular references together with @EqualsAndHashCode(exclude = {arrayOfConnectedVariableNames})
    @JsonIgnoreProperties("user")
    @ToString.Exclude
    private Set<ServiceOffered> services = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    @JsonProperty("savedSearches")
    @JsonIgnoreProperties("user")
    @ToString.Exclude
    private Set<Search> savedSearches = new HashSet<>();

    public User(String username, LocalDateTime registrationTime, String email, int age, String password, UserType userType) {
        this.username = username;
        this.registrationTime = registrationTime;
        this.email = email;
        this.age = age;
        this.password = password;
        this.userType = userType;
    }

    public User(String name,  String email, String password, UserType userType, String fullName, Gender gender) {
        this.username = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.fullName = fullName;
        this.gender = gender;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", registrationTime=" + registrationTime +
                ", description='" + description + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", contact=" + contact +
                ", userType=" + userType +
                ", pets=" + pets +
                ", services=" + services +
                ", savedSearches=" + savedSearches +
                '}';
    }
}
