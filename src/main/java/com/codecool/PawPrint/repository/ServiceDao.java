package com.codecool.PawPrint.repository;

import com.codecool.PawPrint.model.entity.PetType;
import com.codecool.PawPrint.model.service.ServiceOffered;
import com.codecool.PawPrint.model.service.ServiceSubtype;
import com.codecool.PawPrint.model.service.ServiceType;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface ServiceDao {

    void add(ServiceOffered service);

    @Query
    ServiceOffered findByName(String name);
    List<ServiceOffered> findServices(Set<PetType> petTypeSet, String country, String city, String district,
                                      ServiceType service, ServiceSubtype serviceType);
    List<ServiceOffered> findServices(Set<PetType> petTypeSet, String country, String city, String district,
                                     ServiceType service);
    List<ServiceOffered> getAll();
}
