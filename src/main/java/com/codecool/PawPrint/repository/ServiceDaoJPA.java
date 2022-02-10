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

    // all
    @Override
    public Set<ServiceOffered> findServices(Set<PetType> petTypeSet, String country, String city, String district,
                                            ServiceType serviceType, ServiceSubtype serviceSubtype) {
        Set<ServiceOffered> foundServices = new HashSet<>();

        for (PetType petType : petTypeSet) {
            List<ServiceOffered> foundServicesSublist = serviceRepository.findBySearchCriteria(country, city,
                    district, serviceType, serviceSubtype, petType);
            foundServices.addAll(foundServicesSublist);
        }
        return foundServices;
    }

    // without serviceSubtype
    @Override
    public Set<ServiceOffered> findServices(Set<PetType> petTypeSet, String country, String city, String district, ServiceType serviceType) {
        Set<ServiceOffered> foundServices = new HashSet<>();

        for (PetType petType : petTypeSet) {
            List<ServiceOffered> foundServicesSublist = serviceRepository.findBySearchCriteria(country, city, district, serviceType, petType);
            foundServices.addAll(foundServicesSublist);
        }
        return foundServices;
    }

    // without district
    @Override
    public Set<ServiceOffered> findServices(Set<PetType> petTypeSet, String country, String city, ServiceType serviceType, ServiceSubtype serviceSubtype) {
        Set<ServiceOffered> foundServices = new HashSet<>();

        for (PetType petType : petTypeSet) {
            List<ServiceOffered> foundServicesSublist = serviceRepository.findBySearchCriteria(country, city, serviceType, serviceSubtype, petType);
            foundServices.addAll(foundServicesSublist);
        }
        return foundServices;
    }

    // without serviceSubtype and district
    @Override
    public Set<ServiceOffered> findServices(Set<PetType> petTypeSet, String country, String city, ServiceType serviceType) {
        Set<ServiceOffered> foundServices = new HashSet<>();

        for (PetType petType : petTypeSet) {
            List<ServiceOffered> foundServicesSublist = serviceRepository.findBySearchCriteria(country, city, serviceType, petType);
            foundServices.addAll(foundServicesSublist);
        }
        return foundServices;
    }

    @Override
    public List<ServiceOffered> getAll() {
        return serviceRepository.findAll();
    }

    @Override
    public ServiceOffered findById(int id) {
        return serviceRepository.findById(id);
    }
}
