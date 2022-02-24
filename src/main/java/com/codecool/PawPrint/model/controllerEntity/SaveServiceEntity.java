package com.codecool.PawPrint.model.controllerEntity;

import com.codecool.PawPrint.model.entity.PetType;
import com.codecool.PawPrint.model.service.ServiceSubtype;
import com.codecool.PawPrint.model.service.ServiceType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Data
public class SaveServiceEntity {

    private String username;
    private String serviceName;
    private PetType petType;
    private String country;
    private String district;
    private String city;
    private String street;
    private String number;
    private String floor;
    private String door;
    private String bell;
    private String phone;
    private String email;
    private String openingHours;
    private ServiceType serviceType;
    private ServiceSubtype serviceSubtype;
    private String description;
    private String homepage;
//    private MultipartFile image;
}
