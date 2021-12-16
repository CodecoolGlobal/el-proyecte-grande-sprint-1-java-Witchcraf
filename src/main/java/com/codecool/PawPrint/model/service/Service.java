package com.codecool.PawPrint.model.service;

import com.codecool.PawPrint.model.entity.PetType;

public class Service {

    private int id;
    private PetType petType;
    private double rating;
    private String openingHours;
    private ServiceType type;
    private String service;
    private int serviceLog;

    public Service(PetType petType, ServiceType type) {
        this.petType = petType;
        this.type = type;
    }
}
