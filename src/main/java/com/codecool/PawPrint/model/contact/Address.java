package com.codecool.PawPrint.model.contact;

import lombok.Data;

@Data
public class Address {

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
