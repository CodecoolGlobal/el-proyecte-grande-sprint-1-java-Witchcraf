package com.codecool.PawPrint.repository;

import com.codecool.PawPrint.model.entity.PetType;
import com.codecool.PawPrint.model.service.ServiceOffered;
import com.codecool.PawPrint.model.service.ServiceSubtype;
import com.codecool.PawPrint.model.service.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceOffered, Integer> {

    ServiceOffered findByName(String name);

    @Query(
            "select s " +
                    "from ServiceOffered s " +
                    "where s.contact.address.country = ?1 " +
                    "and s.contact.address.city = ?2 " +
                    "and s.contact.address.district = ?3 " +
                    "and s.serviceType = ?4 " +
                    "and s.serviceSubtype = ?5 " +
                    "and s.petType = ?6"
    )
    ServiceOffered findByCountryAndCityAndDistrictAndServiceTypeAndServiceSubtypeAndPetType(String country, String city, String district, ServiceType serviceType, ServiceSubtype serviceSubtype, PetType petType);

    @Query(
            "select s " +
                    "from ServiceOffered s " +
                    "where s.contact.address.country = ?1 " +
                    "and s.contact.address.city = ?2 " +
                    "and s.contact.address.district = ?3 " +
                    "and s.serviceType = ?4 " +
                    "and s.petType = ?5 "
    )
    ServiceOffered findByCountryAndCityAndDistrictAndServiceTypeAndPetType(String country, String city, String district, ServiceType serviceType, PetType petType);
}
