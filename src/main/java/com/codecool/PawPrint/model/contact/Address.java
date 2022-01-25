package com.codecool.PawPrint.model.contact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @SequenceGenerator(
            name = "address_sequence",
            sequenceName = "address_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "address_sequence"
    )

    private int id;
    private int contactId;
    private String country;
    private String district;
    private String city;
    private String street;
    private String number;
    private String floor;
    private String door;
    private String bell;
    @OneToOne(mappedBy = "address")
    private Contact contact;

    public Address(int contactId, String country, String city, String district) {
        this.contactId = contactId;
        this.country = country;
        this.city = city;
        this.district = district;
    }

    public Address(int contactId, String country, String district, String city, String street, String number) {
        this.contactId = contactId;
        this.country = country;
        this.district = district;
        this.city = city;
        this.street = street;
        this.number = number;
    }

}
