package com.codecool.PawPrint.model.service;

import com.codecool.PawPrint.model.contact.Contact;
import com.codecool.PawPrint.model.entity.PetType;
import lombok.Data;

@Data
public class ServiceOffered {

    private int id;
    private String name;
    private PetType petType;
    private double rating;
    private Contact contact;
    private String openingHours;
    private ServiceType serviceType;
    private ServiceSubtype serviceSubtype;
    private String service;
    private int serviceLog;
    private String description;
    private String serviceHomePage;
    private String reservationUrl;
    private String image;

    public ServiceOffered(String name, PetType petType, ServiceType serviceType, ServiceSubtype serviceSubtype, Contact contact) {
        this.name = name;
        this.petType = petType;
        this.serviceType = serviceType;
        this.serviceSubtype = serviceSubtype;
        this.contact = contact;
    }

    public ServiceOffered(String name, PetType petType, double rating, Contact contact, String openingHours, ServiceType serviceType, ServiceSubtype serviceSubtype, String description, String serviceHomePage) {
        this.name = name;
        this.petType = petType;
        this.rating = rating;
        this.contact = contact;
        this.openingHours = openingHours;
        this.serviceType = serviceType;
        this.serviceSubtype = serviceSubtype;
        this.description = description;
        this.serviceHomePage = serviceHomePage;
    }
}
