package com.codecool.PawPrint.model.controllerEntity;

import com.codecool.PawPrint.model.service.ServiceSubtype;
import com.codecool.PawPrint.model.service.ServiceType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SearchService {

    private int userId;
    private String country;
    private String city;
    private String district;
    private ServiceType serviceType;
    private ServiceSubtype serviceSubtype;
    @JsonProperty
    private boolean isDogOnly;
    @JsonProperty
    private boolean isCatOnly;
    @JsonProperty
    private boolean isBothOnly;
    @JsonProperty
    private boolean isAllDog;
    @JsonProperty
    private boolean isAllCat;
    @JsonProperty
    private boolean isAllPetType;

}
