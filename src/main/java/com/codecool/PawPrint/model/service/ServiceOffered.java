package com.codecool.PawPrint.model.service;

import com.codecool.PawPrint.model.contact.Contact;
import com.codecool.PawPrint.model.entity.PetType;
import com.codecool.PawPrint.model.entity.Search;
import com.codecool.PawPrint.model.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@JsonIgnoreProperties(value = {"user", "searches"})
@EqualsAndHashCode(exclude = {"user", "searches"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "service")
public class ServiceOffered {

    @Id
    @SequenceGenerator(
            name = "service_sequence",
            sequenceName = "service_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "service_sequence"
    )

    private int id;
    private String name;
    private PetType petType;
    private double rating;
//    @OneToOne(cascade = CascadeType.MERGE)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    private Contact contact;
    @ManyToOne(fetch = FetchType.EAGER) // produces error if user is added in initializer
    @JsonProperty("user")
    @JsonIgnoreProperties("services")
    @ToString.Exclude
    private User user;
    private String openingHours;
    private ServiceType serviceType;
    private ServiceSubtype serviceSubtype;
//    private String service;
    private int serviceLog;
    private String description;
    private String homepage;
    private String reservationUrl;
    private String image;
    @ManyToMany(mappedBy = "searchedServices")
    @JsonProperty("searches")
    @ToString.Exclude
    private Set<Search> searches = new HashSet<>();

    public ServiceOffered(String name, PetType petType, ServiceType serviceType, ServiceSubtype serviceSubtype, Contact contact) {
        this.name = name;
        this.petType = petType;
        this.serviceType = serviceType;
        this.serviceSubtype = serviceSubtype;
        this.contact = contact;
    }

    public ServiceOffered(String name, PetType petType, double rating, Contact contact, String openingHours, ServiceType serviceType, ServiceSubtype serviceSubtype, String description, String homepage) {
        this.name = name;
        this.petType = petType;
        this.rating = rating;
        this.contact = contact;
        this.openingHours = openingHours;
        this.serviceType = serviceType;
        this.serviceSubtype = serviceSubtype;
        this.description = description;
        this.homepage = homepage;
    }
}
