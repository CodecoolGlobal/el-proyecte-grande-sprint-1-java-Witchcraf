package com.codecool.PawPrint.repository;

import com.codecool.PawPrint.model.entity.PetType;
import com.codecool.PawPrint.model.service.ServiceOffered;
import com.codecool.PawPrint.model.service.ServiceSubtype;
import com.codecool.PawPrint.model.service.ServiceType;

import java.util.Set;

public interface ServiceDao {

    void add(ServiceOffered service);
    ServiceOffered findByName(String name);
    Set<ServiceOffered> findServices(Set<PetType> petTypeSet, String country, String city, String district,
                                     ServiceType service, ServiceSubtype serviceType);
    Set<ServiceOffered> findServices(Set<PetType> petTypeSet, String country, String city, String district,
                                     ServiceType service);
    Set<ServiceOffered> getAll();
}
