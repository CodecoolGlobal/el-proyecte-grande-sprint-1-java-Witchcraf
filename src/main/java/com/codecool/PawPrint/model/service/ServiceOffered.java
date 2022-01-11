package com.codecool.PawPrint.model.service;

import com.codecool.PawPrint.model.contact.Contact;
import com.codecool.PawPrint.model.entity.PetType;
import lombok.Data;

@Data
public class ServiceOffered {

    private int id;
    private PetType petType;
    private double rating;
    private Contact contact;
    private String openingHours;
    private ServiceType serviceType;
    private ServiceSubtype serviceSubtype;
    private String service;
    private int serviceLog;

    public ServiceOffered(PetType petType, ServiceType serviceType, ServiceSubtype serviceSubtype, Contact contact) {
        this.petType = petType;
        this.serviceType = serviceType;
        this.serviceSubtype = serviceSubtype;
        this.contact = contact;
    }
}
