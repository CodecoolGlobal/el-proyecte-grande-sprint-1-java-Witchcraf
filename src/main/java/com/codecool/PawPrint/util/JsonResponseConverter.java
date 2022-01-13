package com.codecool.PawPrint.util;

import com.codecool.PawPrint.model.entity.PetType;
import com.codecool.PawPrint.model.service.ServiceType;

public class JsonResponseConverter {

    public static PetType getPetTypeFromJsonString(String petType){
        if(petType.equalsIgnoreCase("cat")){
            return PetType.CAT;
        }
        if(petType.equalsIgnoreCase("dog")){
            return PetType.DOG;
        }
        if(petType.equalsIgnoreCase("Cat&Dog")){
            return PetType.CATANDDOG;
        }
        return PetType.NONE;
    }

    public static ServiceType getServiceTypeFromJsonString(String serviceType) {
        if(serviceType.equalsIgnoreCase("restaurant")){
            return ServiceType.RESTAURANT;
        }
        if(serviceType.equalsIgnoreCase("wellness")){
            return ServiceType.WELLNESS;
        }
        if(serviceType.equalsIgnoreCase("shelter")){
            return ServiceType.SHELTER;
        }
        if(serviceType.equalsIgnoreCase("hospital")){
            return ServiceType.HOSPITAL;
        }
        return null;
    }
}
