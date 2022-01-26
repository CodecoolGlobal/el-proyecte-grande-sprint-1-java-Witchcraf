package com.codecool.PawPrint.repository;

import com.codecool.PawPrint.model.entity.PetType;
import com.codecool.PawPrint.model.service.ServiceOffered;
import com.codecool.PawPrint.model.service.ServiceSubtype;
import com.codecool.PawPrint.model.service.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Qualifier("serviceDaoJPA")
@Primary
public class ServiceDaoJPA implements ServiceDao {

    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceDaoJPA(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public void add(ServiceOffered service) {
        serviceRepository.save(service);
    }

    @Override
    public ServiceOffered findByName(String name) {
        return serviceRepository.findByName(name);
    }

    @Override
    public Set<ServiceOffered> findServices(Set<PetType> petTypeSet, String country, String city, String district, ServiceType serviceType, ServiceSubtype serviceSubtype) {
        Set<ServiceOffered> foundServices = new HashSet<>();

        for (PetType petType : petTypeSet) {
            foundServices.add(serviceRepository.findByCountryAndCityAndDistrictAndServiceTypeAndServiceSubtypeAndPetType(country, city, district, serviceType, serviceSubtype, petType));
        }
        return foundServices;
    }

    @Override
    public Set<ServiceOffered> findServices(Set<PetType> petTypeSet, String country, String city, String district, ServiceType serviceType) {
        Set<ServiceOffered> foundServices = new HashSet<>();

        for (PetType petType : petTypeSet) {
            foundServices.add(serviceRepository.findByCountryAndCityAndDistrictAndServiceTypeAndPetType(country, city, district, serviceType, petType));
        }
        return foundServices;
    }

    @Override
    public List<ServiceOffered> getAll() {
        return serviceRepository.findAll();
    }
}
