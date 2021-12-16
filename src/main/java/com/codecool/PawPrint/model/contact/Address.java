package com.codecool.PawPrint.model.contact;

public class Address {

    private int id;
    private Contact contact;
    private String country;
    private String region;
    private String city;
    private String street;
    private String number;
    private String floor;
    private String door;
    private String bell;

    public Address(Contact contact, String country, String city) {
        this.contact = contact;
        this.country = country;
        this.city = city;
    }
}
