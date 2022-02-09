package com.codecool.PawPrint.repository;

import com.codecool.PawPrint.model.entity.PetType;
import com.codecool.PawPrint.model.service.ServiceOffered;
import com.codecool.PawPrint.model.service.ServiceSubtype;
import com.codecool.PawPrint.model.service.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceOffered, Integer> {

    ServiceOffered findByName(String name);
    ServiceOffered findById(int id);

    // search by all
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
    List<ServiceOffered> findBySearchCriteria(String country, String city, String district, ServiceType serviceType,
                                              ServiceSubtype serviceSubtype, PetType petType);

    // search without serviceSubtype
    @Query(
            "select s " +
                    "from ServiceOffered s " +
                    "where s.contact.address.country = ?1 " +
                    "and s.contact.address.city = ?2 " +
                    "and s.contact.address.district = ?3 " +
                    "and s.serviceType = ?4 " +
                    "and s.petType = ?5 "
    )
    List<ServiceOffered> findBySearchCriteria(String country, String city, String district, ServiceType serviceType, PetType petType);

    // search without district
    @Query(
            "select s " +
                    "from ServiceOffered s " +
                    "where s.contact.address.country = ?1 " +
                    "and s.contact.address.city = ?2 " +
                    "and s.serviceType = ?3 " +
                    "and s.serviceSubtype = ?4 " +
                    "and s.petType = ?5"
    )
    List<ServiceOffered> findBySearchCriteria(String country, String city, ServiceType serviceType, ServiceSubtype serviceSubtype, PetType petType);

    // search without serviceSubtype and district
    @Query(
            "select s " +
                    "from ServiceOffered s " +
                    "where s.contact.address.country = ?1 " +
                    "and s.contact.address.city = ?2 " +
                    "and s.serviceType = ?3 " +
                    "and s.petType = ?4 "
    )
    List<ServiceOffered> findBySearchCriteria(String country, String city, ServiceType serviceType, PetType petType);
}
