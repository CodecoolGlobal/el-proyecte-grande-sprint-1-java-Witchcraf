package com.codecool.PawPrint.model.controllerEntity;

import com.codecool.PawPrint.model.service.ServiceSubtype;
import com.codecool.PawPrint.model.service.ServiceType;
import lombok.Data;

@Data
public class SearchService {

    private int userId;
    private String country;
    private String city;
    private String district;
    private ServiceType serviceType;
    private ServiceSubtype serviceSubtype;
    private boolean isDogOnly;
    private boolean isCatOnly;
    private boolean isBothOnly;
    private boolean isAllDog;
    private boolean isAllCat;
    private boolean isAllPetType;

}
